package com.dev.backend.devstore.service;

import com.dev.backend.devstore.domain.product.Product;
import com.dev.backend.devstore.domain.transaction.Transaction;
import com.dev.backend.devstore.domain.transaction.TransactionRepository;
import com.dev.backend.devstore.domain.transaction.TransactionRequestDTO;
import com.dev.backend.devstore.domain.user.User;
import com.dev.backend.devstore.repositories.ProductRepository;
import com.dev.backend.devstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Transaction createTransaction(TransactionRequestDTO transactionDTO){
        User user = this.userRepository.getReferenceById(transactionDTO.userId());

        Transaction transaction = new Transaction();

        transaction.setProductSize(transactionDTO.productSize());
        transaction.setValor(transactionDTO.valor());
        transaction.setUser(user);

        for (String productId : transactionDTO.productIds()) {
            Product product = this.productRepository.getReferenceById(productId);
            transaction.getProducts().add(product);
        }

        return this.transactionRepository.save(transaction);
    }

    public List<Transaction> listTransactions(){
        return this.transactionRepository.findAll();
    }
}
