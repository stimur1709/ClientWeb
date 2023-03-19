package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto extends Dto {

    private String username;

    @Size(min = 2, max = 255, message = "{message.firstname}")
    @NotNull(message = "{message.firstname}")
    private String firstname;

    @Size(min = 2, max = 255, message = "{message.lastname}")
    @NotNull(message = "{message.lastname}")
    private String lastname;

    private Date regTime;

    @NotNull(message = "{message.notContact}")
    private List<UserContactDto> userContact;

    @NotNull(message = "{message.notRole}")
    private List<UserRoleDto> userRoles;

    @Size(min = 5, max = 255, message = "message.passwordValid")
    @NotNull(message = "{message.passwordValid}")
    private String password;

    @Override
    @NotNull(message = "{message.notId}")
    public Integer getId() {
        return super.getId();
    }
}
