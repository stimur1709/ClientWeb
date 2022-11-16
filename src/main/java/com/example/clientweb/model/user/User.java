package com.example.clientweb.model.user;

import com.example.clientweb.model.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@javax.persistence.Entity
@Table(name = "users")
@Schema(description = "Сущность пользователя")
public class User extends Entity {

    private String password;

    @Column(columnDefinition = "DATE NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regTime;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;

    private String firstname;

    private String lastname;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserContact> userContact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user2Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonManagedReference
    private List<UserRole> userRoles;

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
