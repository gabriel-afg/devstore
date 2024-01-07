package com.dev.backend.devstore.domain.user;

public record UserResponseDTO(
        String id,
        String name,
        String email,
        String password,
        String address,
        String phone,
        String verificationCode,
        UserRole role
){
}
