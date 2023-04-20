package com.example.clientweb.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserRatings extends Model {

    private int userId;
    private int authorId;
    private int itemId;
    private int value;

}
