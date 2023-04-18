package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.UserContactDto;
import com.example.clientweb.data.dto.UserDto;
import com.example.clientweb.data.model.User;
import com.example.clientweb.data.model.UserContact;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.UserContactRepository;
import com.example.clientweb.repository.UserRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.Generator;
import com.example.clientweb.util.MessageLocale;
import com.example.clientweb.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ModelServiceImpl<User, UserDto, UserRepository> {

    private final PasswordEncoder passwordEncoder;
    private final UserContactRepository userContactRepository;
    private final Generator generator;

    @Autowired
    public UserService(UserRepository repository, MessageLocale messageLocale, PasswordEncoder passwordEncoder,
                       UserContactRepository userContactRepository, Generator generator,
                       ModelMapperUtil<User, UserDto> modelMapper) {
        super(repository,messageLocale, modelMapper);
        this.passwordEncoder = passwordEncoder;
        this.userContactRepository = userContactRepository;
        this.generator = generator;
    }

    @Override
    public UserDto save(UserDto dto) throws SaveException {
        User user = findById(dto.getId());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        changeMail(dto, user);
        return super.save(dto);
    }

    private void changeMail(UserDto dto, User user) throws SaveException {
        UserContactDto contactNew = dto.getUserContact().get(0);
        UserContact contactOld = user.getUserContact().get(0);
        if (!contactNew.getContact().equals(contactOld.getContact())) {
            Optional<UserContact> contact = userContactRepository.findByContactIgnoreCase(contactNew.getContact());
            if (contact.isEmpty()) {
                userContactRepository.delete(contactOld);
                userContactRepository.save(new UserContact(user, contactNew.getType(), contactNew.getContact(), generator.getSecretCode()));
            } else if (!contact.get().getUser().getId().equals(user.getId())) {
                throw new SaveException(messageLocale.getMessage("message.mailBusy"));
            }
        }
    }

}
