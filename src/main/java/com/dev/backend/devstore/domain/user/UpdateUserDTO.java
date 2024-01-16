package com.dev.backend.devstore.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        String id,
        String name,
        String address,
        String phone
) {}
