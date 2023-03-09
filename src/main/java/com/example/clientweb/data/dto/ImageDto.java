package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ImageDto extends Dto implements Serializable {
    private String path;
    private String name;
}