package com.example.clientweb.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@RedisHash("blacklist")
public class Blacklist {

    @Id
    private String id;

    public String token;

    public Blacklist(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
