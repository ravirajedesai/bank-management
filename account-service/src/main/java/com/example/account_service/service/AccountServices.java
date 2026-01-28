package com.example.account_service.service;

import com.example.account_service.entity.Accounts;

public interface AccountServices {
    Accounts createAccount(Accounts accounts);
    Accounts getAccountByAccountNo(Long accountId);
    public void updateBalance(Long accountNo,Double amount);
}
