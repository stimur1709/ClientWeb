package com.example.clientweb.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    private List<Author> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
