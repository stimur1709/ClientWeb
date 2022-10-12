package com.example.clientweb.service;

import com.example.clientweb.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean jwtLogin(AuthenticationDTO authenticationDTO) {


        return false;
    }
}
