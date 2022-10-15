package com.example.clientweb.util;

import com.example.clientweb.dto.RegistrationDTO;
import com.example.clientweb.service.UserContactService;
import com.example.clientweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    private final UserService userService;
    private final UserContactService userContactService;

    @Autowired
    public RegistrationValidator(UserService userService, UserContactService userContactService) {
        this.userService = userService;
        this.userContactService = userContactService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationDTO registrationDTO = (RegistrationDTO) target;

        if (userService.findUserByUsername(registrationDTO.getUsername()).isPresent())
            errors.rejectValue("username", "", "Пользователь с таким" +
                    " логином уже существует");

        if (userContactService.findByMail(registrationDTO.getEmail()).isPresent())
            errors.rejectValue("email", "", "Пользователь с такой" +
                    " почтой уже существует");
    }
}