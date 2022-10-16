package com.example.clientweb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class Generator {

    private final Random random;

    @Autowired
    public Generator(Random random) {
        this.random = random;
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
            result = value == 1 || value == 21 || value == 31 || value == 41 || value == 51 ? " секунду"
                    : value == 2 || value == 3 || value == 4 || value == 22 || value == 23 || value == 24 || value == 32 || value == 33 || value == 34 || value == 42 || value == 43 || value == 44 || value == 52 || value == 53 || value == 54 ? " секунды"
                    : " секунд";
        } else {
            value = 5 - time / 60000;
            result = value == 1 ? " минуту" : value == 5 ? " минут" : " минуты";
        }

        return "Вход заблокирован. Попробуйте через " + value + result;
    }

    public String generatorTextBadContact(int result) {
        return result == 1 ? "Пароль введён неверно. У вас осталось " + result + " попытка"
                : "Пароль введён неверно. У вас осталось " + result + " попытки";
    }
}
