package com.example.clientweb.repository;

import com.example.clientweb.data.model.ContactType;
import com.example.clientweb.data.model.UserContact;

import java.util.Optional;

public interface UserContactRepository extends ModelRepository<UserContact> {

    Optional<UserContact> findByContactAndType(String mail, ContactType type);

    Optional<UserContact> findByContactIgnoreCase(String contact);

}
