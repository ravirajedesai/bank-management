package com.example.customer_service.controller;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.service.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServices services;

    @GetMapping
    public ResponseEntity<Page<Customer>>
    getAllCustomers(@RequestParam(defaultValue = "0") int pageNo,
                    @RequestParam(defaultValue = "2") int pageSize,
                    @RequestParam(defaultValue = "customerName") String sortBy,
                    @RequestParam(defaultValue = "asc") String sortDir){
        Page<Customer> getAll=services.getAllCustomers(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(getAll);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(services.getCustomerById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        services.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(services.createCustomer(customer));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer>
                            updateCustomer(@PathVariable Long id,
                                           @RequestBody Customer customer){
        return ResponseEntity.ok(services.updateCustomer(id,customer));
    }
    @GetMapping("/name")
    public ResponseEntity<CustomerDto>
                        showCustomerById(@RequestParam Long customerId){
        return ResponseEntity.ok(services.findByCustomerId(customerId));
    }
}
