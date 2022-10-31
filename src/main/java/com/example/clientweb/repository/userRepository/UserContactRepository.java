package com.example.clientweb.repository.userRepository;

import com.example.clientweb.model.user.ContactType;
import com.example.clientweb.model.user.UserContact;
import com.example.clientweb.repository.ModelEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactRepository extends ModelEntityRepository<UserContact> {

    Optional<UserContact> findByContactAndType(String mail, ContactType type);

    UserContact findByUser_UsernameAndType(String username, ContactType type);

}
