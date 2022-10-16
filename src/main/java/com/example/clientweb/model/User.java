package com.example.clientweb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@Schema(description = "Сущность пользователя")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

    @Column(columnDefinition = "DATE NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regTime;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private int balance;

    @Column(unique = true, columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String firstname;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String lastname;

    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private int loginAttempts;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserContact> userContact;


    public User(String password, String username, String firstname, String lastname) {
        this.password = password;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.regTime = new Date();
    }

    public User() {

    }
}
