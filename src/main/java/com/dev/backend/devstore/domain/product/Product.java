package com.dev.backend.devstore.domain.product;

import com.dev.backend.devstore.domain.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String slug;
    private Integer price;
    private String description;
    private Boolean featured;
    private String image;

    @ManyToMany(mappedBy = "products")
    @JsonBackReference
    private Set<Transaction> transactions = new HashSet<>();

    public Product(ProductRequestDTO data){
        this.description = data.description();
        this.title = data.title();
        this.slug = data.slug();
        this.image = data.image();
        this.featured = data.featured();
        this.price = data.price();
    }
}
