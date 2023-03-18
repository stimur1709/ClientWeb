package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }
}
