package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDtoForAuthor extends AuthorDto {

    private List<ItemDto> items;

}