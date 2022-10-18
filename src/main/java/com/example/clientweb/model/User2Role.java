package com.example.clientweb.model;

import com.example.clientweb.model.key.KeyUser2Role;

import javax.persistence.*;

@Entity
@Table(name = "user2role")
public class User2Role {

    @EmbeddedId
    private KeyUser2Role id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private UserRole role;
}
