package com.dev.backend.devstore.service;

import com.dev.backend.devstore.domain.user.UpdateUserDTO;
import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.domain.user.UserResponseDTO;
import com.dev.backend.devstore.repositories.UserRepository;
import com.dev.backend.devstore.util.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public UserResponseDTO registerUser(User user) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email already in use");
        }else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            User savedUser = userRepository.save(user);

            emailService.sendVerificationEmail(user);

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

    public boolean verify(String verificationCode){
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()){
            return false;
        }
        else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }

    public Optional<User> findById(String id){
        return this.userRepository.findById(id);
    }

    public UserResponseDTO updateUser(UpdateUserDTO data) {
        User user = userRepository.findById(data.id()).orElse(null);
        if (user == null) {
            return null;
        }

        if (data.name() != null) {
            user.setName(data.name());
        }
        if (data.address() != null) {
            user.setAddress(data.address());
        }
        if (data.phone() != null) {
            user.setPhone(data.phone());
        }

        User updatedUser = userRepository.save(user);

        return UserResponseDTO.fromUser(updatedUser);
    }
}
