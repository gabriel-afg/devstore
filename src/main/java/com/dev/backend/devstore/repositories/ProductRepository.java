package com.dev.backend.devstore.repositories;

import com.dev.backend.devstore.domain.product.Product;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByFeaturedIsTrue();

    List<Product> findBySlug(String slug);

    List<Product> findByTitleContainingIgnoreCase(@Nonnull String title);
}
