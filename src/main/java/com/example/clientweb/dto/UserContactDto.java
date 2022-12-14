package com.example.clientweb.dto;

import com.example.clientweb.model.user.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactDto extends Dto {

    private ContactType type;

    private String contact;

}
