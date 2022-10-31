package com.example.clientweb.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EducationDto extends ModelDto {

    private String title;

    private String image;

    private String description;

    private double rate;

    private double popularity;

    private int duration;
}
