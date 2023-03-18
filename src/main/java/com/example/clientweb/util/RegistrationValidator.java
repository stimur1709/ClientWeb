package com.example.clientweb.util;

import com.example.clientweb.data.dto.RegistrationDto;
import com.example.clientweb.data.model.user.ContactType;
import com.example.clientweb.repository.UserContactRepository;
import com.example.clientweb.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final UserContactRepository userContactRepository;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    @Autowired
    public RegistrationValidator(UserRepository userRepository, UserContactRepository userContactRepository, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.userContactRepository = userContactRepository;
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

        if (userRepository.findByUsernameIgnoreCase(registrationDTO.getUsername()).isPresent()) {
            String message = messageSource.getMessage("message.loginBusy", null, localeResolver.resolveLocale(request));
            errors.rejectValue("username", "", message);
        }

        if (userContactRepository.findByContactAndType(registrationDTO.getEmail(), ContactType.MAIL).isPresent()) {
            String message = messageSource.getMessage("message.mailBusy", null, localeResolver.resolveLocale(request));
            errors.rejectValue("email", "", message);
        }
    }
}
