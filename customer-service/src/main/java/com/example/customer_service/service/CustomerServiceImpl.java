package com.example.customer_service.service;

import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.entity.Customer;
import com.example.customer_service.exceptions.CustomerNotFound;
import com.example.customer_service.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerServices{

    private final CustomerRepo customerRepo;

    @Override
    public Page<Customer> getAllCustomers(int pageNo,
                                          int pageSize,
                                          String sortBy,
                                          String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return customerRepo.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(()->new CustomerNotFound("Customer Not Found: "+id));
    }

    @Override
    public void deleteCustomerById(Long id) {
    if (!customerRepo.existsById(id)) {
        throw new CustomerNotFound("Customer Not Found: "+id);
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
                .orElseThrow(()->new CustomerNotFound("Customer Not Found :"+id));

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setMobileNo(customer.getMobileNo());
        existingCustomer.setCustomerEmail(customer.getCustomerEmail());

        return customerRepo.save(existingCustomer);
    }

    @Override
    public CustomerDto findByCustomerId(Long customerId) {

        Customer newcustomer=customerRepo.findById(customerId)
                .orElseThrow(()->
                        new CustomerNotFound("Customer Not Found: "+customerId));

        CustomerDto dto=new CustomerDto(
                newcustomer.getCustomerId(),
                newcustomer.getCustomerName(),
                newcustomer.getCustomerEmail(),
                newcustomer.getMobileNo()
        );
        return dto;
    }
}
