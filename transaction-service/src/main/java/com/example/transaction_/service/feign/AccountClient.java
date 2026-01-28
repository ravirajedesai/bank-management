package com.example.transaction_.service.feign;

import com.example.transaction_.service.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/accounts/get")
    AccountDto getAccountByAccountId(@RequestParam Long accountId);

    @PutMapping("/accounts/balance")
    void updateBalance(@RequestParam Long accountId,
                       @RequestParam Double signedAmount);
}
