package com.example.clientweb.controller.userController;

import com.example.clientweb.data.dto.PasswordDto;
import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.service.userService.UserService;
import com.example.clientweb.util.BindingResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;
    private final BindingResultResponse bindingResultResponse;

    @Autowired
    public UserController(UserService userService, BindingResultResponse bindingResultResponse) {
        this.userService = userService;
        this.bindingResultResponse = bindingResultResponse;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userService.getUserDTO(token), HttpStatus.OK);
    }

    @PostMapping("/change/password")
    @Operation(summary = "Смена пароля",
            description = "Запрос на смену пароля у пользователя.")
    public ResponseEntity<?> changePassword(@RequestBody @Valid PasswordDto passwordDTO, BindingResult bindingResult,
                                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.CONFLICT);
        }

        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userService.changePassword(token, passwordDTO), HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<Boolean> changeMail(HttpServletRequest request, @RequestBody String mail) {
        String token = request.getHeader("Authorization").substring(7);
        boolean result = userService.changeMail(token, mail);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
