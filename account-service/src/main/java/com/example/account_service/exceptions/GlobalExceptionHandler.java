package com.example.account_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<Map<String,Object>> AccountNotFoundGlobalExceptionHandler(AccountNotFound ex){
        Map<String,Object> response=new HashMap<>();
        response.put("Message", ex.getMessage());
        response.put("Code",HttpStatus.NOT_FOUND.value());
        response.put("TimeStamp", LocalDateTime.now());
        response.put("Status",HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
