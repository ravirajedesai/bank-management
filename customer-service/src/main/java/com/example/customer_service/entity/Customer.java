package com.example.customer_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @Column(name = "customer_email",nullable = false,unique = true)
    private String customerEmail;

    @Column(name = "mobile_no",nullable = false,unique = true)
    private Long mobileNo;
}
