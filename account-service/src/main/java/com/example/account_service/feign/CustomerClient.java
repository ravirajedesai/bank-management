package com.example.account_service.feign;

import com.example.account_service.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/customers/name")
    CustomerDto getCustomerById(@RequestParam Long customerId);
}
