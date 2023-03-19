package com.example.clientweb.security;

import com.example.clientweb.data.model.User;
import com.example.clientweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ClientUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);

        if (user.isPresent()) {
            return new ClientUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Пользователь не найден");
    }
}
