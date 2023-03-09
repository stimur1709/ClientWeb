package com.example.clientweb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Component
@Slf4j
public class Generator {

    private final Random random;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    @Autowired
    public Generator(Random random, MessageSource messageSource, LocaleResolver localeResolver, HttpServletRequest request) {
        this.random = random;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.request = request;
    }

    public String getSecretCode() {
        String code = 100 + random.nextInt(999 - 100 + 1) + " " + (100 + random.nextInt(999 - 100 + 1));
        log.info("Generation code " + code);
        return code;
    }

    public String generatorTextBlockContact(long time) {
        long value;
        String result;
        if (time > 240000) {
            value = (300000 - time) / 1000;
            String second = ' ' + messageSource.getMessage("message.second", null, localeResolver.resolveLocale(request));
            String seconds2 = ' ' + messageSource.getMessage("message.seconds2", null, localeResolver.resolveLocale(request));
            result = value == 1 || value == 21 || value == 31 || value == 41 || value == 51 ? second
                    : value == 2 || value == 3 || value == 4 || value == 22 || value == 23 || value == 24 || value == 32 || value == 33 || value == 34 || value == 42 || value == 43 || value == 44 || value == 52 || value == 53 || value == 54 ? " секунды"
                    : seconds2;
        } else {
            String minute = ' ' + messageSource.getMessage("message.minute", null, localeResolver.resolveLocale(request));
            String minutes1 = ' ' + messageSource.getMessage("message.minutes1", null, localeResolver.resolveLocale(request));
            String minutes2 = ' ' + messageSource.getMessage("message.minutes2", null, localeResolver.resolveLocale(request));
            value = 5 - time / 60000;
            result = value == 1 ? minute : value == 5 ? minutes1 : minutes2;
        }

        return "Вход заблокирован. Попробуйте через " + value + result;
    }

    public String generatorTextBadContact(int result) {
        String password = messageSource.getMessage("message.password", null, localeResolver.resolveLocale(request));
        String enteredIncorrectly = ' ' + messageSource.getMessage("message.enteredIncorrectly", null, localeResolver.resolveLocale(request));
        String attempt = ' ' + messageSource.getMessage("message.attempt", null, localeResolver.resolveLocale(request));
        String attempts = ' ' + messageSource.getMessage("message.attempts", null, localeResolver.resolveLocale(request));
        return result == 1 ? password + enteredIncorrectly + result + attempt
                : password + enteredIncorrectly + result + attempts;
    }
}
