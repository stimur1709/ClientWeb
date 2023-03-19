package com.example.clientweb.repository;

import com.example.clientweb.data.model.User;

import java.util.Optional;

public interface UserRepository extends ModelRepository<User> {

    Optional<User> findByUsernameIgnoreCase(String username);

}
