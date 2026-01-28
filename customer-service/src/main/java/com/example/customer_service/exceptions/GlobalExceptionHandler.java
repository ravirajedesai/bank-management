package com.example.customer_service.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<Map<String,Object>> globalCustomerNotFound(CustomerNotFound ex){
        Map<String,Object> response=new HashMap<>();
        response.put("Message",ex.getMessage());
        response.put("Error", HttpStatus.NOT_FOUND.name());
        response.put("code",HttpStatus.NOT_FOUND.value());
        response.put("TimeStamp", LocalDateTime.now());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
