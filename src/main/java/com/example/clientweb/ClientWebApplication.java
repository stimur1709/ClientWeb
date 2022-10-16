package com.example.clientweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class ClientWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientWebApplication.class, args);
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
