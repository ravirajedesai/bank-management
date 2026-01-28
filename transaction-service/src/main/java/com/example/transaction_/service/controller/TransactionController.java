package com.example.transaction_.service.controller;

import com.example.transaction_.service.entity.Transactions;
import com.example.transaction_.service.services.TransactionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServices services;

    @PostMapping
    public ResponseEntity<Transactions>
                createTransaction(@RequestBody Transactions transactions){
        return ResponseEntity.ok(services.createTransactions(transactions));
    }
}
