package com.dev.backend.devstore.repositories;

import com.dev.backend.devstore.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);

    Optional<User> findById(String id);

    User findByVerificationCode(String verificationCode);
}
