package com.example.clientweb.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Author extends Model {

    private String name;
    private String description;
//
//
//    @Formula("(select count(*) from user_ratings u where u.user_id = CURRENT_USER_ID())")
//    private int likes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item2author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonIgnoreProperties("authors")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
