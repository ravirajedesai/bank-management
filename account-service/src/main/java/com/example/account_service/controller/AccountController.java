package com.example.account_service.controller;

import com.example.account_service.entity.Accounts;
import com.example.account_service.service.AccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServices services;

    @PostMapping()
    public ResponseEntity<Accounts>
                    createAccount(@RequestBody Accounts accounts){
        return ResponseEntity.ok(services.createAccount(accounts));
    }
    @GetMapping("/get")
    public ResponseEntity<Accounts>
            getAccountByAccountNumber(@RequestParam Long accountId){
        return ResponseEntity.ok(services.getAccountByAccountNo(accountId));
    }
    @PutMapping("/balance")
    public ResponseEntity<Void>
                        updateBalance(@RequestParam("accountId") Long accountId,
                                      @RequestParam("amount") Double amount){
        services.updateBalance(accountId, amount);
        return ResponseEntity.noContent().build();
    }
}
