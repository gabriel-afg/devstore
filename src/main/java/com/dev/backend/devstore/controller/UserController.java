package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.user.UpdateUserDTO;
import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.domain.user.UserResponseDTO;
import com.dev.backend.devstore.infra.security.TokenService;
import com.dev.backend.devstore.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


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
            return "Usuario verificado com sucesso";
        } else {
            return "Não foi possivel efetuar a verificação";
        }
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UpdateUserDTO data) {
        UserResponseDTO updatedUser = userService.updateUser(data);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser);
    }

}
