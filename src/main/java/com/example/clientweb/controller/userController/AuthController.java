package com.example.clientweb.controller.userController;

import com.example.clientweb.data.dto.AuthDto;
import com.example.clientweb.data.dto.AuthenticationDto;
import com.example.clientweb.service.userService.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAuthService userAuthService;

    @Autowired
    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody AuthenticationDto authenticationDTO) {
        AuthDto authDto = userAuthService.jwtLogin(authenticationDTO);
        return new ResponseEntity<>(authDto, authDto.getToken() == null ? HttpStatus.CONFLICT : HttpStatus.OK);
    }
}
