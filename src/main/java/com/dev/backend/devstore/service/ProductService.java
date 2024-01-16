package com.dev.backend.devstore.service;

import com.dev.backend.devstore.domain.product.Product;
import com.dev.backend.devstore.domain.product.ProductRequestDTO;
import com.dev.backend.devstore.domain.product.ProductResponseDTO;
import com.dev.backend.devstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO dados){
        Product product = new Product(dados);

        return this.productRepository.save(product);
    }

    public List<ProductResponseDTO> listProduct(){
        return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    public List<ProductResponseDTO> listProductByTitle(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title).stream().map(ProductResponseDTO::new).toList();
    }

    public List<ProductResponseDTO> listProductBySlug(String slug) {
        return productRepository.findBySlug(slug).stream().map(ProductResponseDTO::new).toList();
    }

    public List<ProductResponseDTO> listProductByFeaturedTrue(){
        return productRepository.findByFeaturedIsTrue().stream().map(ProductResponseDTO::new).toList();
    }
}
