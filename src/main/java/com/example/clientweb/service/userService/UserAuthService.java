package com.example.clientweb.service.userService;

import com.example.clientweb.dto.AuthenticationDto;
import com.example.clientweb.model.user.User;
import com.example.clientweb.model.user.UserRole;
import com.example.clientweb.security.JWTUtil;
import com.example.clientweb.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserAuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final BlacklistService blacklistService;
    private final Generator generator;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    @Value("${profile.loginAttempts}")
    private int loginAttempts;

    @Value("${profile.blockTimeMinUser}")
    private long blockTimeMinUser;

    @Autowired
    public UserAuthService(UserService userService, AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                           BlacklistService blacklistService, Generator generator, MessageSource messageSource,
                           LocaleResolver localeResolver, HttpServletRequest request) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.blacklistService = blacklistService;
        this.generator = generator;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.request = request;
    }

    public Map<String, Object> jwtLogin(AuthenticationDto authenticationDTO) {
        Optional<User> userOptional = userService.findUserByUsername(authenticationDTO.getUsername());
        String userNotFound = messageSource.getMessage("message.userNotFound", null, localeResolver.resolveLocale(request));
        if (userOptional.isEmpty())
            return getErrorResponse(userNotFound);

        User user = userOptional.get();
        Date now = new Date();
        long difTime = now.getTime() - user.getLoginTime().getTime();

        if (difTime > blockTimeMinUser * 60000) {
            updateLogin(user);
        }

        if (user.getLoginAttempts() >= loginAttempts && difTime < blockTimeMinUser * 60000)
            return getErrorResponse(generator.generatorTextBlockContact(difTime));

        if (!blacklistService.findToken(authenticationDTO.getUsername()))
            blacklistService.delete(authenticationDTO.getUsername());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);
            updateLogin(user);
        } catch (BadCredentialsException e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userService.save(user);

            if (user.getLoginAttempts() >= loginAttempts) {
                user.setLoginTime(new Date());
                userService.save(user);
                return getErrorResponse(generator.generatorTextBlockContact(difTime));
            }

            if (user.getLoginAttempts() > 2)
                return getErrorResponse(generator.generatorTextBadContact(loginAttempts - user.getLoginAttempts()));

            return getErrorResponse(userNotFound);

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

    public void updateLogin(User user) {
        user.setLoginAttempts(0);
        user.setLoginTime(new Date());
        userService.save(user);
    }

}
