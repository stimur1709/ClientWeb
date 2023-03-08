package com.example.clientweb.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Author extends Model {

    private String photo;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "item2author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

}
