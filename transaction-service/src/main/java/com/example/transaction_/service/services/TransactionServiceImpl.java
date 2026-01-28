package com.example.transaction_.service.services;

import com.example.transaction_.service.dto.AccountDto;
import com.example.transaction_.service.entity.TransactionType;
import com.example.transaction_.service.entity.Transactions;
import com.example.transaction_.service.feign.AccountClient;
import com.example.transaction_.service.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionServices{

    private final TransactionRepo repo;
    private final AccountClient accountClient;

    @Override
    @Transactional
    public Transactions createTransactions(Transactions transactions) {

        AccountDto account=
                accountClient.getAccountByAccountId(transactions.getAccountId());

        Transactions newTransaction= new Transactions();
        newTransaction.setAccountId(transactions.getAccountId());
        newTransaction.setCustomerName(account.getCustomerName());
        newTransaction.setCustomerEmail(account.getCustomerEmail());
        newTransaction.setMobileNo(account.getMobileNo());
        newTransaction.setType(transactions.getType());
        newTransaction.setTransactionTime(LocalDateTime.now());
        newTransaction.setAmount(transactions.getAmount());

    Double signedAmount=
            transactions.getType()== TransactionType.CREDIT
            ? transactions.getAmount()
            : -transactions.getAmount();

    accountClient.updateBalance(account.getAccountId(), signedAmount);


        return repo.save(newTransaction);
    }
}
