package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationDTO {

    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 2, max = 100, message = "Логин должен быть от 2 до 100 символов длиной")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 100, message = "Пароль должен быть от 2 до 100 символов длиной")
    private String password;
}
