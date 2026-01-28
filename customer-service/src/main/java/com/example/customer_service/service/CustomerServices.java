package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;

import java.util.List;

public interface CustomerServices {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id,Customer customer);
    CustomerDto findByCustomerId(Long customerId);
}
