package com.example.clientweb.repository;

import com.example.clientweb.model.Role;
import com.example.clientweb.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole getByRole(Role role);
}
