package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto extends Dto {

    private String username;

    private String firstname;

    private String lastname;

    private Date regTime;

    private List<UserContactDto> userContact;

}
