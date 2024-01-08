package com.dev.backend.devstore.repositories;

import com.dev.backend.devstore.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);

    User findByVerificationCode(String verificationCode);
}
