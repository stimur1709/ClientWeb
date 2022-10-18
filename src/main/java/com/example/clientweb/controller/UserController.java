package com.example.clientweb.controller;

import com.example.clientweb.dto.PasswordDTO;
import com.example.clientweb.dto.UserDTO;
import com.example.clientweb.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userProfileService.getUserDTO(token), HttpStatus.OK);
    }

    @PostMapping("/change/password")
    public ResponseEntity<Boolean> changePassword(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userProfileService.changePassword(token, passwordDTO), HttpStatus.OK);
    }

    @PostMapping("/change/mail")
    public ResponseEntity<Boolean> changeMail(HttpServletRequest request, @RequestBody PasswordDTO passwordDTO) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userProfileService.changePassword(token, passwordDTO), HttpStatus.OK);
    }
}
