package com.example.clientweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("BookShop")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("stimur1709@mail.ru")
                                                .url("https://github.com/stimur1709")
                                                .name("Timur Safin")
                                )
                );
    }
}
