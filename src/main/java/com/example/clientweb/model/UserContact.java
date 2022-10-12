package com.example.clientweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_contact")
@Schema(description = "Сущность контакта пользователя")
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", columnDefinition = "INT")
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private short approved;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String code;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private int codeTrails;

    @Temporal(TemporalType.TIMESTAMP)
    private Date codeTime;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String contact;

    public UserContact(User user, ContactType type, String contact, String code) {
        this.user = user;
        this.type = type;
        this.contact = contact;
        this.code = code;
        this.codeTime = new Date();
    }

    public UserContact() {

    }
}
