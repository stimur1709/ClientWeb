package com.example.clientweb.repository;

import com.example.clientweb.data.model.user.ContactType;
import com.example.clientweb.data.model.user.UserContact;

import java.util.Optional;

public interface UserContactRepository extends ModelRepository<UserContact> {

    Optional<UserContact> findByContactAndType(String mail, ContactType type);

    UserContact findByContactIgnoreCase(String contact);

}
