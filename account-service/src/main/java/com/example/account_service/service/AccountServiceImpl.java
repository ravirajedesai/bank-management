package com.example.account_service.service;

import com.example.account_service.dto.CustomerDto;
import com.example.account_service.entity.Accounts;
import com.example.account_service.exceptions.AccountNotFound;
import com.example.account_service.feign.CustomerClient;
import com.example.account_service.repository.AccountsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountServices{

    private final AccountsRepo accountsRepo;
    private final CustomerClient customerClient;

    @Override
    public Accounts createAccount(Accounts accounts) {

        CustomerDto newCustomer=
                customerClient.getCustomerById(accounts.getCustomerId());
        if (newCustomer == null) {
            throw new AccountNotFound(
                    "Customer not found with id: " + accounts.getCustomerId()
            );
        }
        Accounts newAccount= new Accounts();

        newAccount.setCustomerId(accounts.getCustomerId());
        newAccount.setCustomerName(newCustomer.getCustomerName());
        newAccount.setCustomerEmail(newCustomer.getCustomerEmail());
        newAccount.setMobileNo(newCustomer.getMobileNo());
        newAccount.setBalance(accounts.getBalance());

        return accountsRepo.save(newAccount);
    }

    @Override
    public Accounts getAccountByAccountNo(Long accountId) {
        return accountsRepo.findByAccountId(accountId);
    }

    @Override
    public void updateBalance(Long accountNo,Double amount){
        Accounts accBalance=accountsRepo.findByAccountId(accountNo);
        if (accBalance==null)
        {
            throw new AccountNotFound("Account Not Found"+accountNo);
        }
         accBalance.setBalance(accBalance.getBalance() + amount);
         accountsRepo.save(accBalance);
    }
}
