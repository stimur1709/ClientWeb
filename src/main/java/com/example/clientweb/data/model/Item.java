package com.example.clientweb.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Item extends Model {

    private String title;

    private String image;

    private String description;

    private double rate;

    private double popularity;

    private int duration;

    @Column(name = "type_id")
    private int typeId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @PrePersist
    private void createDate() {
        this.createdDate = new Date();
    }

    @ManyToMany
    @JoinTable(name = "item2author",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonManagedReference
    private List<Author> authors;

}
