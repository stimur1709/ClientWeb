package com.example.clientweb.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EducationDto extends Dto {

    private String title;

    private String image;

    private String description;

    private double rate;

    private double popularity;

    private int duration;

    private Date updatedDate;

    private Date createdDate;

}
