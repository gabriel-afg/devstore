package com.dev.backend.devstore.domain.transaction;

import com.dev.backend.devstore.domain.product.ProductSize;

import java.util.List;

public record TransactionRequestDTO(
        ProductSize productSize,
        String userId,
        Integer valor,
        List<String> productIds

) {
}
