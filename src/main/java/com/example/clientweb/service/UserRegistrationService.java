package com.example.clientweb.service;

import com.example.clientweb.dto.RegistrationDTO;
import com.example.clientweb.model.*;
import com.example.clientweb.util.Generator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserRegistrationService {

    private final UserProfileService userProfileService;
    private final UserContactService userContactService;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;

    public UserRegistrationService(UserProfileService userProfileService, UserContactService userContactService,
                                   Generator generator, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.userProfileService = userProfileService;
        this.userContactService = userContactService;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    public User registrationUser(RegistrationDTO registrationDTO) {
        User user = new User(passwordEncoder.encode(registrationDTO.getPassword()), registrationDTO.getUsername(),
                registrationDTO.getFirstname(), registrationDTO.getLastname());
        UserContact contact = new UserContact(user, ContactType.MAIL, registrationDTO.getEmail(), generator.getSecretCode());
        user.setUserRoles(Collections.singletonList(userRoleService.gerUserRole(Role.ROLE_USER)));
        userProfileService.save(user);
        userContactService.save(contact);
        return user;
    }
}
