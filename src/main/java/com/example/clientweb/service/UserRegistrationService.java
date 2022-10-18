package com.example.clientweb.service;

import com.example.clientweb.dto.RegistrationDTO;
import com.example.clientweb.model.ContactType;
import com.example.clientweb.model.User;
import com.example.clientweb.model.UserContact;
import com.example.clientweb.util.Generator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserProfileService userProfileService;
    private final UserContactService userContactService;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserProfileService userProfileService, UserContactService userContactService,
                                   Generator generator, PasswordEncoder passwordEncoder) {
        this.userProfileService = userProfileService;
        this.userContactService = userContactService;
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
    }

    public User registrationUser(RegistrationDTO registrationDTO) {
        User user = new User(passwordEncoder.encode(registrationDTO.getPassword()), registrationDTO.getUsername(),
                registrationDTO.getFirstname(), registrationDTO.getLastname());
        UserContact contact = new UserContact(user, ContactType.MAIL, registrationDTO.getEmail(), generator.getSecretCode());

        userProfileService.save(user);
        userContactService.save(contact);
        return user;
    }
}
