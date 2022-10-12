package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private String password;

    private String username;

    private String firstname;

    private String lastname;

    private String contact;

    public PersonDTO() {
    }
}
