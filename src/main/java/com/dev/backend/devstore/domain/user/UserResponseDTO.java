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
    public static UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress(),
                user.getPhone(),
                user.getVerificationCode(),
                user.getRole()
        );
    }
}
