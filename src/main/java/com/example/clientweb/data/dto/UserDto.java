package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto extends Dto {

    private String username;

    private String firstname;

    private String lastname;

    private Date regTime;

    @NotNull(message = "Не контакт")
    private List<UserContactDto> userContact;

    private String password;

    @Override
    @NotNull(message = "Не id")
    public Integer getId() {
        return super.getId();
    }
}
