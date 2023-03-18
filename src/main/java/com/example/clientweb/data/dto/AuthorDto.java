package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorDto extends Dto implements Serializable {

    private String name;
    private String description;
    private ImageDto image;

}