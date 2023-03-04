package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class PasswordDto {

    private String passwordOld;

    @Size(min = 5, max = 100, message = "{message.password}")
    private String passwordNew;
}
