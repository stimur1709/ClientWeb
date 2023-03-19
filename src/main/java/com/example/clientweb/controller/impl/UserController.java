package com.example.clientweb.controller.impl;

import com.example.clientweb.controller.ModelControllerImpl;
import com.example.clientweb.data.dto.AuthDto;
import com.example.clientweb.data.dto.AuthenticationDto;
import com.example.clientweb.data.dto.RegistrationDto;
import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.data.model.User;
import com.example.clientweb.service.UserAuthService;
import com.example.clientweb.service.impl.UserService;
import com.example.clientweb.util.BindingResultResponse;
import com.example.clientweb.util.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController extends ModelControllerImpl<UserDto, User, UserService> {

    private final RegistrationValidator registrationValidator;
    private final UserAuthService userAuthService;

    @Autowired
    protected UserController(UserService userService, RegistrationValidator registrationValidator, UserAuthService userAuthService) {
        super(userService);
        this.registrationValidator = registrationValidator;
        this.userAuthService = userAuthService;
    }

    @Override
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDto> getDto(@PathVariable int id) {
        return super.getDto(id);
    }

    @Override
    @GetMapping("/profiles")
    public ResponseEntity<Page<UserDto>> getPage(int itemType, int page, int size, boolean reverse, String sort) {
        return super.getPage(itemType, page, size, reverse, sort);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody AuthenticationDto authenticationDTO) {
        AuthDto authDto = userAuthService.jwtLogin(authenticationDTO);
        return new ResponseEntity<>(authDto, authDto.getToken() == null ? HttpStatus.CONFLICT : HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDto registrationDTO,
                                          BindingResult bindingResult) {
        registrationValidator.validate(registrationDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(BindingResultResponse.getMessage(bindingResult), HttpStatus.OK);
        }
        return new ResponseEntity<>(userAuthService.registrationUser(registrationDTO), HttpStatus.OK);
    }
}
