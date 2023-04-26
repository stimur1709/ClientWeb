package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRatingsDto extends Dto {

    private Integer itemId;
    private Integer authorId;
    private short value;

}
