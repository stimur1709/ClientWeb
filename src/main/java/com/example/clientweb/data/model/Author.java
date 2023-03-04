package com.example.clientweb.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Author extends Model {

    private String photo;

    private String name;

    private String description;

}
