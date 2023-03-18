package com.example.clientweb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class Generator {

    private final Random random;
    private final MessageLocale messageLocale;

    @Autowired
    public Generator(Random random, MessageLocale messageLocale) {
        this.random = random;
        this.messageLocale = messageLocale;
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
            String second = ' ' + messageLocale.getMessage("message.second");
            String seconds2 = ' ' + messageLocale.getMessage("message.second2");
            result = value == 1 || value == 21 || value == 31 || value == 41 || value == 51 ? second
                    : value == 2 || value == 3 || value == 4 || value == 22 || value == 23 || value == 24 || value == 32 || value == 33 || value == 34 || value == 42 || value == 43 || value == 44 || value == 52 || value == 53 || value == 54 ? " секунды"
                    : seconds2;
        } else {
            String minute = ' ' + messageLocale.getMessage("message.minute");
            String minutes1 = ' ' + messageLocale.getMessage("message.minutes1");
            String minutes2 = ' ' + messageLocale.getMessage("message.minutes2");
            value = 5 - time / 60000;
            result = value == 1 ? minute : value == 5 ? minutes1 : minutes2;
        }

        return messageLocale.getMessage("message.entranceBlocked") + value + result;
    }

    public String generatorTextBadContact(int result) {
        String password = messageLocale.getMessage("message.password");
        String enteredIncorrectly = ' ' + messageLocale.getMessage("message.enteredIncorrectly");
        String attempt = ' ' + messageLocale.getMessage("message.attempt");
        String attempts = ' ' + messageLocale.getMessage("message.attempts");
        return result == 1 ? password + enteredIncorrectly + result + attempt
                : password + enteredIncorrectly + result + attempts;
    }
}
