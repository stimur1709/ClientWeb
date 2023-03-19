package com.example.clientweb.data.model;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item2author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
