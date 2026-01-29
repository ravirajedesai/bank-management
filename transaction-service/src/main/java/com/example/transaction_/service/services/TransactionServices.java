package com.example.transaction_.service.services;

import com.example.transaction_.service.entity.Transactions;
import org.springframework.data.domain.Page;

public interface TransactionServices {
    Page<Transactions> showAllTransactions(int pageNo,
                                           int pageSize,
                                           String sortBy,
                                           String sortDir);
    Transactions createTransactions(Transactions transactions);
}
