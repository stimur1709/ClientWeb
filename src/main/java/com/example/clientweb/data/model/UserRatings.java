package com.example.clientweb.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_ratings")
public class UserRatings extends Model {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "author_id")
    private int authorId;

    private short value;
}
