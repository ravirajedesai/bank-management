package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerServices{

    private final CustomerRepo customerRepo;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer Not Found: "+id));
    }

    @Override
    public void deleteCustomerById(Long id) {
    if (!customerRepo.existsById(id)) {
        throw new RuntimeException("Customer Not Found: "+id);
    }
        customerRepo.deleteById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer=customerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer Not Found :"+id));
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setMobileNo(customer.getMobileNo());
        existingCustomer.setCustomerEmail(customer.getCustomerEmail());

        return customerRepo.save(existingCustomer);
    }

    @Override
    public CustomerDto findByCustomerId(Long customerId) {

        Customer newcustomer=customerRepo.findById(customerId)
                .orElseThrow(()->
                        new RuntimeException("Customer Not Found: "+customerId));

        CustomerDto dto=new CustomerDto(
                newcustomer.getCustomerId(),
                newcustomer.getCustomerName(),
                newcustomer.getCustomerEmail(),
                newcustomer.getMobileNo()
        );
        return dto;
    }
}
