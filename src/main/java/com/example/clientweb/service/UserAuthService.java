package com.example.clientweb.service;

import com.example.clientweb.data.dto.AuthDto;
import com.example.clientweb.data.dto.AuthenticationDto;
import com.example.clientweb.data.dto.RegistrationDto;
import com.example.clientweb.data.model.*;
import com.example.clientweb.repository.UserContactRepository;
import com.example.clientweb.repository.UserRepository;
import com.example.clientweb.security.JWTUtil;
import com.example.clientweb.util.Generator;
import com.example.clientweb.util.MessageLocale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final BlacklistService blacklistService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final MessageLocale messageLocale;
    private final Generator generator;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final UserContactRepository userContactRepository;
    @Value("${profile.loginAttempts}")
    private int loginAttempts;

    @Value("${profile.blockTimeMinUser}")
    private long blockTimeMinUser;


    @Autowired
    public UserAuthService(UserRepository userRepository, BlacklistService blacklistService, AuthenticationManager authenticationManager, JWTUtil jwtUtil, MessageLocale messageLocale, Generator generator, UserRoleService userRoleService, PasswordEncoder passwordEncoder, UserContactRepository userContactRepository) {
        this.userRepository = userRepository;
        this.blacklistService = blacklistService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.messageLocale = messageLocale;
        this.generator = generator;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.userContactRepository = userContactRepository;
    }

    public AuthDto jwtLogin(AuthenticationDto authenticationDTO) {
        Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(authenticationDTO.getUsername());
        String userNotFound = messageLocale.getMessage("message.userNotFound");
        if (userOptional.isEmpty()) {
            return new AuthDto(userNotFound);
        }

        User user = userOptional.get();
        Date now = new Date();
        long difTime = now.getTime() - user.getLoginTime().getTime();

        if (difTime > blockTimeMinUser * 60000) {
            updateLogin(user);
        }

        if (user.getLoginAttempts() >= loginAttempts && difTime < blockTimeMinUser * 60000)
            return new AuthDto(generator.generatorTextBlockContact(difTime));

        if (!blacklistService.findToken(authenticationDTO.getUsername()))
            blacklistService.delete(authenticationDTO.getUsername());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);
            updateLogin(user);
        } catch (BadCredentialsException e) {
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            userRepository.save(user);

            if (user.getLoginAttempts() >= loginAttempts) {
                user.setLoginTime(new Date());
                userRepository.save(user);
                return new AuthDto(generator.generatorTextBlockContact(difTime));
            }

            if (user.getLoginAttempts() > 2)
                return new AuthDto(generator.generatorTextBadContact(loginAttempts - user.getLoginAttempts()));

            return new AuthDto(userNotFound);

        }

        List<String> roles = user.getUserRoles().stream().map(UserRole::getRole).map(Enum::toString).collect(Collectors.toList());

        return new AuthDto(jwtUtil.generateToken(user), roles, user.getId());
    }

    public AuthDto registrationUser(RegistrationDto registrationDTO) {
        User user = new User(passwordEncoder.encode(registrationDTO.getPassword()), registrationDTO.getUsername(),
                registrationDTO.getFirstname(), registrationDTO.getLastname());
        UserRole role = userRoleService.gerUserRole(Role.ROLE_USER);
        user.setUserRoles(Collections.singletonList(role));
        userRepository.save(user);
        userContactRepository.save(new UserContact(user, ContactType.MAIL, registrationDTO.getEmail(), generator.getSecretCode()));
        return new AuthDto(jwtUtil.generateToken(user), Collections.singletonList(role.getRole().toString()), user.getId());
    }

    private void updateLogin(User user) {
        user.setLoginAttempts(0);
        user.setLoginTime(new Date());
        userRepository.save(user);
    }

    public Integer getAuthUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
           try {
               return Integer.parseInt(authentication.getName());
           } catch (NumberFormatException ex) {
               return null;
           }
        } else {
            return null;
        }
    }
}
