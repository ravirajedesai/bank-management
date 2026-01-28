package com.example.account_service.repository;

import com.example.account_service.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Accounts,Long> {
    Accounts findByAccountId(Long accountNo);
}
