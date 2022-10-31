package com.example.clientweb.service.userService;

import com.example.clientweb.dto.UserDto;
import com.example.clientweb.model.user.Role;
import com.example.clientweb.model.user.UserRole;
import com.example.clientweb.repository.userRepository.UserRoleRepository;
import com.example.clientweb.service.ModelEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends ModelEntityServiceImpl<UserRole, UserDto, UserRoleRepository> {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        super(userRoleRepository);
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole gerUserRole(Role role) {
        return userRoleRepository.getByRole(role);
    }
}
