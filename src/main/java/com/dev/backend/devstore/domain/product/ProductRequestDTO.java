package com.dev.backend.devstore.domain.product;

import jakarta.annotation.Nonnull;

public record ProductRequestDTO(
        @Nonnull
        String title,
        @Nonnull
        String slug,
        @Nonnull
        String description,
        @Nonnull
        String image,
        @Nonnull
        Integer price
) {

}
