package com.example.clientweb.service;

import com.example.clientweb.data.model.Role;
import com.example.clientweb.data.model.UserRole;
import com.example.clientweb.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole gerUserRole(Role role) {
        return userRoleRepository.getByRole(role);
    }
}
