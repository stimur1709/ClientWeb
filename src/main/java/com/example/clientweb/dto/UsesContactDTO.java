package com.example.clientweb.dto;

import com.example.clientweb.model.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsesContactDTO {

    private ContactType type;

    private String contact;

}
