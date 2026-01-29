package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerServices {
    Page<Customer> getAllCustomers(int pageNo,
                                   int pageSize,
                                   String sortBy,
                                   String sortDir);
    Customer getCustomerById(Long id);
    void deleteCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id,Customer customer);

    CustomerDto findByCustomerId(Long customerId);
}
