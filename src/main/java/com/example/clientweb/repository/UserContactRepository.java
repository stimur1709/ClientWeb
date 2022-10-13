package com.example.clientweb.repository;

import com.example.clientweb.model.ContactType;
import com.example.clientweb.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Integer> {

    Optional<UserContact> findByContactAndType(String mail, ContactType type);
}
