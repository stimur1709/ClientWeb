package com.example.clientweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto extends ModelDto{

    private String username;

    private String firstname;

    private String lastname;

    private Date regTime;

    private List<UserContactDto> userContact;

}
