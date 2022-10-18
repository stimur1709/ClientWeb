package com.example.clientweb.service;

import com.example.clientweb.dto.PasswordDTO;
import com.example.clientweb.dto.UserDTO;
import com.example.clientweb.model.ContactType;
import com.example.clientweb.model.User;
import com.example.clientweb.model.UserContact;
import com.example.clientweb.repository.UserRepository;
import com.example.clientweb.security.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserContactService userContactService;

    @Autowired
    public UserProfileService(UserRepository userRepository, ModelMapper modelMapper, JWTUtil jwtUtil,
                              PasswordEncoder passwordEncoder, UserContactService userContactService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userContactService = userContactService;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public void updateLogin(User user) {
        user.setLoginAttempts(0);
        user.setLoginTime(new Date());
        save(user);
    }

    public void updateLogin(User user, Date date) {
        user.setLoginTime(date);
        save(user);
    }

    public void updateLogin(User user, int loginAttempts) {
        user.setLoginAttempts(loginAttempts);
        save(user);
    }

    public UserDTO getUserDTO(String token) {
        String username = jwtUtil.validateTokenAndRetrieveClaim(token);
        return convertToUserDTO(getUserByUsername(username));
    }

    public boolean changePassword(String token, PasswordDTO passwordDTO) {
        User user = userRepository.getByUsername(jwtUtil.validateTokenAndRetrieveClaim(token));

        if (passwordEncoder.matches(passwordDTO.getPasswordOld(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getPasswordNew()));
            save(user);
            return true;
        }
        return false;
    }

    public boolean changeMail(String token, String mail) {
        String username = jwtUtil.validateTokenAndRetrieveClaim(token);
        if (userContactService.findByMail(mail).isEmpty()) {
            UserContact userContact = userContactService.getByUsernameAndType(username, ContactType.MAIL);
            userContact.setContact(mail);
            userContactService.save(userContact);
            return true;
        }
        return false;
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
