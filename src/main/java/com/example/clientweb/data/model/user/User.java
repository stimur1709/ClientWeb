package com.example.clientweb.data.model.user;

import com.example.clientweb.data.model.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@javax.persistence.Entity
@Table(name = "users")
@Schema(description = "Сущность пользователя")
public class User extends Model {

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

    @Column(columnDefinition = "DATE NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @OneToMany(mappedBy = "user")
    @NotNull(message = "Не указан контакт")
    private List<UserContact> userContact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user2Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<UserRole> userRoles;

    public User(String password, String username, String firstname, String lastname) {
        this.password = password;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.regTime = new Date();
        this.loginTime = new Date();
    }

    public User() {
    }

    @Override
    @NotNull(message = "Не указан id")
    public Integer getId() {
        return super.getId();
    }
}
