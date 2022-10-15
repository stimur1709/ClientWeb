package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 100, message = "Пароль должен быть от 5 до 100 символов длиной")
    private String password;

    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 2, max = 100, message = "Логин должен быть от 2 до 100 символов длиной")
    private String username;

    @NotEmpty(message = "Фамилия не должна быть пустым")
    @Size(min = 2, max = 100, message = "Фамилия должна быть от 2 до 100 символов длиной")
    private String firstname;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String lastname;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть в формате *****@***.**")
    private String email;

    public RegistrationDTO() {
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
