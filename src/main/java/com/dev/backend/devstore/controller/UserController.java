package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.domain.user.UserRequestDTO;
import com.dev.backend.devstore.domain.user.UserResponseDTO;
import com.dev.backend.devstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO data){
        User user = new User(data);
        UserResponseDTO userSaved = userService.registerUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

}
