package com.example.clientweb.data.dto;

import com.example.clientweb.data.model.user.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactDto extends Dto {

    private ContactType type;
    private String contact;

}
