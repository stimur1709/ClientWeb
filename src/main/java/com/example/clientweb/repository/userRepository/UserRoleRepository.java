package com.example.clientweb.repository.userRepository;

import com.example.clientweb.model.user.Role;
import com.example.clientweb.model.user.UserRole;
import com.example.clientweb.repository.ModelEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends ModelEntityRepository<UserRole> {

    UserRole getByRole(Role role);

}
