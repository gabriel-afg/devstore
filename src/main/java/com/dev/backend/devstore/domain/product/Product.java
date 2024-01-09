package com.dev.backend.devstore.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
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

    public Product(ProductRequestDTO data){
        this.description = data.description();
        this.title = data.title();
        this.slug = data.slug();
        this.image = data.image();
        this.featured = data.featured();
        this.price = data.price();
    }
}
