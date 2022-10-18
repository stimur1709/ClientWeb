package com.example.clientweb.service;

import com.example.clientweb.model.ClientUserDetails;
import com.example.clientweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    private final UserProfileService userProfileService;

    @Autowired
    public ClientUserDetailsService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userProfileService.findUserByUsername(username);

        if (user.isPresent())
            return new ClientUserDetails(user.get());

        throw new UsernameNotFoundException("Пользователь не найден");
    }
}
