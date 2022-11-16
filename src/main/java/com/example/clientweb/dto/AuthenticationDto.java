package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationDto {

    @Size(min = 2, max = 100, message = "{message.username}")
    private String username;

    @Size(min = 5, max = 100, message = "{message.password}")
    private String password;
}
