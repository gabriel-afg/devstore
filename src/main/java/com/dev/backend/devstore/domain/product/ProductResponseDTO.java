package com.dev.backend.devstore.domain.product;

import java.util.List;

public record ProductResponseDTO(
        String id,
        String title,
        String slug,
        String description,
        String image,
        Boolean featured,
        Integer price
) {
    public ProductResponseDTO(Product product){
        this(
                product.getId(), 
                product.getTitle(), 
                product.getSlug(),
                product.getDescription(),
                product.getImage(),
                product.getFeatured(),
                product.getPrice()
        );
    }

}
