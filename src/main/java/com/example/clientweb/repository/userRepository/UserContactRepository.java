package com.example.clientweb.repository.userRepository;

import com.example.clientweb.data.model.user.ContactType;
import com.example.clientweb.data.model.user.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Integer> {

    Optional<UserContact> findByContactAndType(String mail, ContactType type);

    UserContact findByUser_UsernameAndType(String username, ContactType type);

}
