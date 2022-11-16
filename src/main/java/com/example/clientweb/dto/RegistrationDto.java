package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDto {

    @Size(min = 5, max = 100, message = "{message.password}")
    private String password;

    @Size(min = 2, max = 100, message = "{message.username}")
    private String username;

    @Size(min = 2, max = 100, message = "{message.firstname}")
    private String firstname;

    @Size(min = 2, max = 100, message = "{message.lastname}")
    private String lastname;

    @NotEmpty(message = "{message.validMail}")
    @Email(message = "{message.validMail}")
    private String email;

    public RegistrationDto() {
    }
}
