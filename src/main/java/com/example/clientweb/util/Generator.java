package com.example.clientweb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
