package com.example.clientweb.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Image extends Model {

    private String name;
    private long size;

    public Image(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public Image() {
    }

}