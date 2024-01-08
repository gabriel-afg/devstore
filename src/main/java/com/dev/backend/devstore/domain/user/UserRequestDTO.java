package com.dev.backend.devstore.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public record UserRequestDTO(

        @Nonnull
        String name,

        @Nonnull
        @Email
        String email,

        @Nonnull
        @Size(min = 6, message = "A senha deve conter no minimo 6 digitos")
        String password,

        String address,
        String phone,
        String verificationCode,

        @Nonnull
        UserRole role
) { }
