package com.example.clientweb.service;

import com.example.clientweb.dto.PersonDTO;
import com.example.clientweb.model.ContactType;
import com.example.clientweb.model.User;
import com.example.clientweb.model.UserContact;
import com.example.clientweb.util.Generator;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserService userService;
    private final UserContactService userContactService;
    private final Generator generator;

    public UserRegistrationService(UserService userService, UserContactService userContactService, Generator generator) {
        this.userService = userService;
        this.userContactService = userContactService;
        this.generator = generator;
    }

    public void registrationUser(PersonDTO personDTO) {
        User user = new User(personDTO.getPassword(), personDTO.getUsername(),
                personDTO.getFirstname(), personDTO.getLastname());
        UserContact contact = new UserContact(user, ContactType.MAIL, personDTO.getContact(), generator.getSecretCode());

        userService.save(user);
        userContactService.save(contact);
    }
}
