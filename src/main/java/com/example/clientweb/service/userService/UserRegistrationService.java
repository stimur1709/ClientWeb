package com.example.clientweb.service.userService;

import com.example.clientweb.dto.RegistrationDto;
import com.example.clientweb.model.user.*;
import com.example.clientweb.security.JWTUtil;
import com.example.clientweb.util.Generator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserRegistrationService {

    private final UserService userService;
    private final UserContactService userContactService;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final JWTUtil jwtUtil;

    public UserRegistrationService(UserService userService, UserContactService userContactService,
                                   Generator generator, PasswordEncoder passwordEncoder, UserRoleService userRoleService,
                                   JWTUtil jwtUtil) {
        this.userService = userService;
        this.userContactService = userContactService;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, Object> registrationUser(RegistrationDto registrationDTO) {
        User user = new User(passwordEncoder.encode(registrationDTO.getPassword()), registrationDTO.getUsername(),
                registrationDTO.getFirstname(), registrationDTO.getLastname());
        UserContact contact = new UserContact(user, ContactType.MAIL, registrationDTO.getEmail(), generator.getSecretCode());
        user.setUserRoles(Collections.singletonList(userRoleService.gerUserRole(Role.ROLE_USER)));
        userService.save(user);
        userContactService.save(contact);
        List<String> roles = user.getUserRoles().stream().map(UserRole::getRole).map(Enum::toString).collect(Collectors.toList());

        return Map.of("result", true, "token", jwtUtil.generateToken(user), "role", roles);
    }
}
