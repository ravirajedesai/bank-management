package com.example.transaction_.service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private String messagee;
    private String status;
    private Object order;

}
