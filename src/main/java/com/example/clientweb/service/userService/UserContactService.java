package com.example.clientweb.service.userService;

import com.example.clientweb.model.user.ContactType;
import com.example.clientweb.model.user.UserContact;
import com.example.clientweb.repository.userRepository.UserContactRepository;
import com.example.clientweb.service.ModelEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactService extends ModelEntityServiceImpl<UserContact, UserContactRepository> {

    private final UserContactRepository userContactRepository;

    @Autowired
    public UserContactService(UserContactRepository userContactRepository) {
        super(userContactRepository);
        this.userContactRepository = userContactRepository;
    }

    public Optional<UserContact> findByMail(String mail) {
        return userContactRepository.findByContactAndType(mail, ContactType.MAIL);
    }

    public UserContact getByUsernameAndType(String username, ContactType type) {
        return userContactRepository.findByUser_UsernameAndType(username, type);
    }
}
