package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.domain.user.UserRequestDTO;
import com.dev.backend.devstore.domain.user.UserResponseDTO;
import com.dev.backend.devstore.infra.security.TokenService;
import com.dev.backend.devstore.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO data) throws MessagingException, UnsupportedEncodingException {
        User user = new User(data);
        UserResponseDTO userSaved = userService.registerUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Optional<User>> findUserById(@RequestHeader("Authorization") String token){
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        String idUser = this.tokenService.getIdUser(token);

        Optional<User> user = this.userService.findById(idUser);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(userService.verify(code)){
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

}
