package com.example.clientweb.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthDto {

    private String message;
    private String token;
    private int id;
    private List<String> role;

    public AuthDto(String message) {
        this.message = message;
    }

    public AuthDto(String token, List<String> role, int id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }
}
