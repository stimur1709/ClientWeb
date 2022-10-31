package com.example.clientweb.repository.userRepository;

import com.example.clientweb.model.user.User;
import com.example.clientweb.repository.ModelEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ModelEntityRepository<User> {

    Optional<User> findByUsernameIgnoreCase(String username);

    User getByUsername(String username);

}
