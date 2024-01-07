package com.dev.backend.devstore.service;

import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.domain.user.UserResponseDTO;
import com.dev.backend.devstore.repositories.UserRepository;
import com.dev.backend.devstore.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email already in use");
        }else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            User savedUser = userRepository.save(user);

            return new UserResponseDTO(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPassword(),
                    savedUser.getAddress(),
                    savedUser.getPhone(),
                    savedUser.getVerificationCode(),
                    savedUser.getRole()
            );
        }
    }
}
