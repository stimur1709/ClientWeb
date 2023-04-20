package com.example.clientweb.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Author extends Model {

    private String name;
    private String description;

    @Formula("0")
    private int likes;

    @Formula("0")
    private int dislikes;

    @Formula("0")
    private Integer rating;

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
