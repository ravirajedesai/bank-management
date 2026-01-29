package com.example.transaction_.service.services;

import com.example.transaction_.service.dto.AccountDto;
import com.example.transaction_.service.dto.TransactionEvent;
import com.example.transaction_.service.entity.TransactionType;
import com.example.transaction_.service.entity.Transactions;
import com.example.transaction_.service.feign.AccountClient;
import com.example.transaction_.service.kafka.KafkaProducer;
import com.example.transaction_.service.repository.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionServices{

    private final TransactionRepo repo;
    private final AccountClient accountClient;
    private final KafkaProducer kafkaProducer;

    @Override
    public Page<Transactions> showAllTransactions(int pageNo,
                                                  int pageSize,
                                                  String sortBy,
                                                  String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return repo.findAll(pageable);
    }

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
        newTransaction.setAmount(transactions.getAmount());
        newTransaction.setTransactionTime(LocalDateTime.now());

    Double signedAmount=
            transactions.getType()== TransactionType.CREDIT
            ? transactions.getAmount()
            : -transactions.getAmount();

    accountClient.updateBalance(account.getAccountId(), signedAmount);

    Transactions savedTransaction=repo.save(newTransaction);

        AccountDto dto= new AccountDto(
                savedTransaction.getTransactionId(),
                savedTransaction.getAccountId(),
                savedTransaction.getCustomerName(),
                savedTransaction.getCustomerEmail(),
                savedTransaction.getMobileNo(),
                savedTransaction.getAmount()
        );

        TransactionEvent event=new TransactionEvent();
        event.setMessagee("Transaction Done...");
        event.setStatus("Transaction saved..");
        event.setOrder(savedTransaction);

        kafkaProducer.sentMessage(event);

        return savedTransaction;
    }
}
