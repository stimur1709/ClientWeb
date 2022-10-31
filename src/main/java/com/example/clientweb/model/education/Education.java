package com.example.clientweb.model.education;

import com.example.clientweb.model.ModelEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class Education extends ModelEntity {

    private String title;

    private String image;

    private String description;

    private double rate;

    private double popularity;

    private int duration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "DATE NOT NULL")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", columnDefinition = "DATE NOT NULL")
    private Date updatedDate;

}
