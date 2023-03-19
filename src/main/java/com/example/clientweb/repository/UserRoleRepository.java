package com.example.clientweb.repository;

import com.example.clientweb.data.model.Role;
import com.example.clientweb.data.model.UserRole;

public interface UserRoleRepository extends ModelRepository<UserRole> {

    UserRole getByRole(Role role);

}
