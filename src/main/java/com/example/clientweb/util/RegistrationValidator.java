package com.example.clientweb.util;

import com.example.clientweb.data.dto.RegistrationDto;
import com.example.clientweb.service.userService.UserContactService;
import com.example.clientweb.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.LocaleResolver;
import reactor.util.annotation.NonNull;

import javax.servlet.http.HttpServletRequest;

@Component
public class RegistrationValidator implements Validator {

    private final UserService userService;
    private final UserContactService userContactService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    @Autowired
    public RegistrationValidator(UserService userService, UserContactService userContactService,
                                 MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        this.userService = userService;
        this.userContactService = userContactService;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.request = request;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RegistrationDto registrationDTO = (RegistrationDto) target;

        if (userService.findUserByUsername(registrationDTO.getUsername()).isPresent()) {
            String message = messageSource.getMessage("message.loginBusy", null, localeResolver.resolveLocale(request));
            errors.rejectValue("username", "", message);
        }

        if (userContactService.findByMail(registrationDTO.getEmail()).isPresent()) {
            String message = messageSource.getMessage("message.mailBusy", null, localeResolver.resolveLocale(request));
            errors.rejectValue("email", "", message);
        }
    }
}
