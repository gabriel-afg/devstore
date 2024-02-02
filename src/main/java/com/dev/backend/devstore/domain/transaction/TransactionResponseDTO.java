package com.dev.backend.devstore.domain.transaction;

import com.dev.backend.devstore.domain.product.Product;
import com.dev.backend.devstore.domain.product.ProductSize;
import com.dev.backend.devstore.domain.user.User;

import java.util.Set;

public record TransactionResponseDTO(
        String id,
        ProductSize productSize,
        Integer valor,
        User user,
        Set<Product> product

) {
    public TransactionResponseDTO(Transaction transaction){
        this(
                transaction.getId(),
                transaction.getProductSize(),
                transaction.getValor(),
                transaction.getUser(),
                transaction.getProducts()
        );
    }
}
