package com.example.clientweb.service;

import com.example.clientweb.dto.AuthenticationDTO;
import com.example.clientweb.model.User;
import com.example.clientweb.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final BlacklistService blacklistService;
    private final UserService userService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                       BlacklistService blacklistService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.blacklistService = blacklistService;
        this.userService = userService;
    }

    public Map<String, String> jwtLogin(AuthenticationDTO authenticationDTO) {
        Optional<User> user = userService.findUserByUsername(authenticationDTO.getUsername());

        if (user.isEmpty())
            return Map.of("message", "Пользователь не существует или неверный пароль");

        if (!blacklistService.findToken(authenticationDTO.getUsername()))
            blacklistService.delete(authenticationDTO.getUsername());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {

            return Map.of("message", "Пользователь не существует или неверный пароль");
        }

        return Map.of("token", jwtUtil.generateToken(authenticationDTO.getUsername()));
    }
}
