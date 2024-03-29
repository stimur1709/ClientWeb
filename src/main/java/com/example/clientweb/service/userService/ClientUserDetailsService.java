package com.example.clientweb.service.userService;

import com.example.clientweb.data.model.user.ClientUserDetails;
import com.example.clientweb.data.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public ClientUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByUsername(username);

        if (user.isPresent())
            return new ClientUserDetails(user.get());

        throw new UsernameNotFoundException("Пользователь не найден");
    }
}
