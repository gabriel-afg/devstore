package com.dev.backend.devstore.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;


public record UserRequestDTO(

        @Nonnull
        String name,

        @Nonnull
        @Email
        String email,

        @Nonnull
        String password,

        String address,
        String phone,
        String verificationCode,

        @Nonnull
        UserRole role
) { }
