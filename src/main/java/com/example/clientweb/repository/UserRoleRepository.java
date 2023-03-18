package com.example.clientweb.repository;

import com.example.clientweb.data.model.user.Role;
import com.example.clientweb.data.model.user.UserRole;

public interface UserRoleRepository extends ModelRepository<UserRole> {

    UserRole getByRole(Role role);

}
