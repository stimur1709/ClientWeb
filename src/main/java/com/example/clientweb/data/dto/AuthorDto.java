package com.example.clientweb.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AuthorDto extends Dto implements Serializable {

    @Size(min = 2, max = 255, message = "{message.firstname}")
    @NotNull(message = "{message.firstname}")
    private String name;
    private String description;
    private ImageDto image;
    private int likes;
    private int dislikes;
    private Integer rating;

    @JsonIgnoreProperties("authors")
    private List<ItemDto> items;
}