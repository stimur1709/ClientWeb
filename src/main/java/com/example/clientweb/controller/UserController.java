package com.example.clientweb.controller;

import com.example.clientweb.dto.PasswordDTO;
import com.example.clientweb.dto.UserDTO;
import com.example.clientweb.service.UserProfileService;
import com.example.clientweb.util.BindingResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserProfileService userProfileService;
    private final BindingResultResponse bindingResultResponse;

    @Autowired
    public UserController(UserProfileService userProfileService, BindingResultResponse bindingResultResponse) {
        this.userProfileService = userProfileService;
        this.bindingResultResponse = bindingResultResponse;
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(userProfileService.getUserDTO(token), HttpStatus.OK);
    }

    @PostMapping("/change/password")
    @Operation(summary = "Смена пароля",
            description = "Запрос на смену пароля у пользователя.")
    public ResponseEntity<Map<String, ?>> changePassword(@RequestBody @Valid PasswordDTO passwordDTO, BindingResult bindingResult,
                                                         HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResultResponse.getMessage(bindingResult), HttpStatus.OK);

        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(Map.of("result", userProfileService.changePassword(token, passwordDTO)), HttpStatus.OK);
    }

    @PostMapping("/change/mail")
    public ResponseEntity<Map<String, ?>> changeMail(HttpServletRequest request, @RequestParam("mail") String mail) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(Map.of("result", userProfileService.changeMail(token, mail)), HttpStatus.OK);
    }
}
