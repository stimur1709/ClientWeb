package com.example.clientweb.service;

import com.example.clientweb.model.User;
import com.example.clientweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public void updateLogin(User user) {
        user.setLoginAttempts(0);
        user.setLoginTime(new Date());
        save(user);
    }

    public void updateLogin(User user, Date date) {
        user.setLoginTime(date);
        save(user);
    }

    public void updateLogin(User user, int loginAttempts) {
        user.setLoginAttempts(loginAttempts);
        save(user);
    }
}
