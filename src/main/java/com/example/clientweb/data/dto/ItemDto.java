package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemDto extends Dto implements Serializable {

    private String title;
    private String image;
    private String description;
    private double rate;
    private double popularity;
    private int duration;
    private int typeId;
    private Date createdDate;
    private List<AuthorDto> authors;

}