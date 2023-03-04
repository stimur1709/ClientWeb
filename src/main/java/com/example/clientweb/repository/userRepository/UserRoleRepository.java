package com.example.clientweb.repository.userRepository;

import com.example.clientweb.data.model.user.Role;
import com.example.clientweb.data.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole getByRole(Role role);

}
