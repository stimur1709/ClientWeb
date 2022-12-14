package com.example.clientweb.controller.userController;

import com.example.clientweb.dto.PasswordDto;
import com.example.clientweb.dto.UserDto;
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
import java.util.Map;

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
    public ResponseEntity<Map<String, ?>> changePassword(@RequestBody @Valid PasswordDto passwordDTO, BindingResult bindingResult,
                                                         HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.OK);

        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(Map.of("result", userService.changePassword(token, passwordDTO)), HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<Map<String, ?>> changeMail(HttpServletRequest request, @RequestBody String mail) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(Map.of("result", userService.changeMail(token, mail)), HttpStatus.OK);
    }
}
