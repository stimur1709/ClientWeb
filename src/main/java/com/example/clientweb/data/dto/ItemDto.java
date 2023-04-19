package com.example.clientweb.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemDto extends Dto implements Serializable {

    @Size(min = 2, max = 255, message = "{message.firstname}")
    @NotNull(message = "{message.firstname}")
    private String title;
    private String description;
    private double rate;
    private double popularity;
    private int duration;
    private int typeId;
    private Date createdDate;
    @JsonIgnoreProperties("items")
    private List<AuthorDto> authors;
    private ImageDto image;

}