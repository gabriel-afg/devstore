package com.dev.backend.devstore.domain.product;

public record ProductResponseDTO(
        String id,
        String title,
        String slug,
        String description,
        String image,
        Integer price
) {
    public ProductResponseDTO(Product product){
        this(
                product.getId(), 
                product.getTitle(), 
                product.getSlug(),
                product.getDescription(),
                product.getImage(),
                product.getPrice()
        );
    }

}
