package com.example.transaction_.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "account_id",nullable = false)
    private Long accountId;

    @Column(name = "customer_id",nullable = false)
    private String customerName;

    @Column(name = "customer_email",nullable = false)
    private String customerEmail;

    @Column(name = "mobile_no",nullable = false)
    private Long mobileNo;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @Column(name = "type",nullable = false)
    private TransactionType type;

    @Column(name = "transaction_time",nullable = false)
    private LocalDateTime transactionTime;
}
