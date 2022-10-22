package com.example.clientweb.service;

import com.example.clientweb.dto.AuthenticationDTO;
import com.example.clientweb.model.User;
import com.example.clientweb.model.UserRole;
import com.example.clientweb.security.JWTUtil;
import com.example.clientweb.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final BlacklistService blacklistService;
    private final UserProfileService userProfileService;
    private final Generator generator;

    @Value("${profile.loginAttempts}")
    private int loginAttempts;

    @Value("${profile.blockTimeMinUser}")
    private long blockTimeMinUser;


    @Autowired
    public UserAuthService(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                           BlacklistService blacklistService, UserProfileService userProfileService, Generator generator) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.blacklistService = blacklistService;
        this.userProfileService = userProfileService;
        this.generator = generator;
    }

    public Map<String, Object> jwtLogin(AuthenticationDTO authenticationDTO) {
        Optional<User> userOptional = userProfileService.findUserByUsername(authenticationDTO.getUsername());
        if (userOptional.isEmpty())
            return getErrorResponse("Пользователь не существует или неверный пароль");

        User user = userOptional.get();
        Date now = new Date();
        long difTime = now.getTime() - user.getLoginTime().getTime();

        if (difTime > blockTimeMinUser * 60000) {
            userProfileService.updateLogin(user);
        }

        if (user.getLoginAttempts() >= loginAttempts && difTime < blockTimeMinUser * 60000)
            return getErrorResponse(generator.generatorTextBlockContact(difTime));

        if (!blacklistService.findToken(authenticationDTO.getUsername()))
            blacklistService.delete(authenticationDTO.getUsername());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);
            userProfileService.updateLogin(user);

        } catch (BadCredentialsException e) {
            userProfileService.updateLogin(user, user.getLoginAttempts() + 1);

            if (user.getLoginAttempts() >= loginAttempts) {
                userProfileService.updateLogin(user, new Date());
                return getErrorResponse(generator.generatorTextBlockContact(difTime));
            }

            if (user.getLoginAttempts() > 2)
                return getErrorResponse(generator.generatorTextBadContact(loginAttempts - user.getLoginAttempts()));

            return getErrorResponse("Пользователь не существует или неверный пароль");

        }
        List<String> roles = user.getUserRoles().stream().map(UserRole::getRole).map(Enum::toString).collect(Collectors.toList());

        return Map.of("result", true, "token", jwtUtil.generateToken(user), "role", roles);
    }

    private Map<String, Object> getErrorResponse(String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", false);
        response.put("message", text);
        return response;
    }
}
