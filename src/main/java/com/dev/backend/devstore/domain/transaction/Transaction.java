package com.dev.backend.devstore.domain.transaction;

import com.dev.backend.devstore.domain.product.Product;
import com.dev.backend.devstore.domain.product.ProductSize;
import com.dev.backend.devstore.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private ProductSize productSize;

    private Integer valor;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "transaction_products",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();

    public Transaction(TransactionResponseDTO data){
        this.productSize = data.productSize();
        this.valor = data.valor();
        this.user = data.user();
        this.products = data.product();
    }
}
