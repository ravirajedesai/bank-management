package com.example.transaction_.service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long customerId;
    private Long accountId;
    private String customerName;
    private String customerEmail;
    private Long mobileNo;
    private Double balance;
}
