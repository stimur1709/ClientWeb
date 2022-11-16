package com.example.clientweb.service.userService;

import com.example.clientweb.model.user.Role;
import com.example.clientweb.model.user.UserRole;
import com.example.clientweb.repository.userRepository.UserRoleRepository;
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
