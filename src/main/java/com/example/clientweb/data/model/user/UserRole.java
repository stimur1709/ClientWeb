package com.example.clientweb.data.model.user;

import com.example.clientweb.data.model.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserRole extends Model {

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user2Role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonBackReference
    private List<User> users;
}
