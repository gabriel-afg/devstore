package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.product.Product;
import com.dev.backend.devstore.domain.product.ProductRequestDTO;
import com.dev.backend.devstore.domain.product.ProductResponseDTO;
import com.dev.backend.devstore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> list(){
        return ResponseEntity.ok(this.service.listProduct());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> listPerTitle(@RequestParam("q") @Valid String title){
        return ResponseEntity.ok(this.service.listProductByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductRequestDTO data){
        System.out.println(data);

        Product newProduct = this.service.createProduct(data);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

}
