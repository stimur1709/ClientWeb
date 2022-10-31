package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String username;

    private String firstname;

    private String lastname;

    private List<UsesContactDto> userContact;

}