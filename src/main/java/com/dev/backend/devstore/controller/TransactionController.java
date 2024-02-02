package com.dev.backend.devstore.controller;

import com.dev.backend.devstore.domain.transaction.Transaction;
import com.dev.backend.devstore.domain.transaction.TransactionRequestDTO;
import com.dev.backend.devstore.domain.transaction.TransactionResponseDTO;
import com.dev.backend.devstore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionDTO){
        Transaction transaction = this.transactionService.createTransaction(transactionDTO);
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(transaction);

        return ResponseEntity.ok().body(transactionResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listTrasactions(){
        List<Transaction> transactions = this.transactionService.listTransactions();

        return ResponseEntity.ok(transactions);
    }
}
