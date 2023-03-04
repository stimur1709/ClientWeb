package com.example.clientweb.service.userService;

import com.example.clientweb.data.model.user.ContactType;
import com.example.clientweb.data.model.user.UserContact;
import com.example.clientweb.repository.userRepository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactService {

    private final UserContactRepository userContactRepository;

    @Autowired
    public UserContactService(UserContactRepository userContactRepository) {
        this.userContactRepository = userContactRepository;
    }

    public Optional<UserContact> findByMail(String mail) {
        return userContactRepository.findByContactAndType(mail, ContactType.MAIL);
    }

    public UserContact getByUsernameAndType(String username, ContactType type) {
        return userContactRepository.findByUser_UsernameAndType(username, type);
    }

    public void save(UserContact userContact) {
        userContactRepository.save(userContact);
    }
}
