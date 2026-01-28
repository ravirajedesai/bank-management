package com.example.transaction_.service.repository;

import com.example.transaction_.service.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transactions,Long> {
}
