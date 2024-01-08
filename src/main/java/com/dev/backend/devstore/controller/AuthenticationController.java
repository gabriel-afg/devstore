package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.user.AuthenticationDTO;
import com.dev.backend.devstore.domain.user.LoginResponseDTO;
import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

            System.out.println(usernamePassword.getPrincipal());
            System.out.println(usernamePassword.getCredentials());
            System.out.println(usernamePassword.isAuthenticated());
            System.out.println("ta aqui");

            var auth = authenticationManager.authenticate(usernamePassword);

            System.out.println("ta aqui2");

            var token = tokenService.generateToken((User)auth.getPrincipal());

            System.out.println("ta aqui3");

            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
