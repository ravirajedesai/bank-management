package com.example.transaction_.service.controller;

import com.example.transaction_.service.entity.Transactions;
import com.example.transaction_.service.services.TransactionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServices services;

    @GetMapping
    public ResponseEntity<Page<Transactions>>
    findAllTransactions(@RequestParam int pageNo,
                        @RequestParam int pageSize,
                        @RequestParam String sortBy,
                        @RequestParam String sortDir){
        return ResponseEntity.ok(services.showAllTransactions(pageNo,pageSize,sortBy,sortDir));
    }
    @PostMapping
    public ResponseEntity<Transactions>
                createTransaction(@RequestBody Transactions transactions){
        return ResponseEntity.ok(services.createTransactions(transactions));
    }
}
