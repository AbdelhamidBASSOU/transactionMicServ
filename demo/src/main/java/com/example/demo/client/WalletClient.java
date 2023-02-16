package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("wallet-service")
public interface WalletClient {
    @GetMapping("/balance/{walletId}")
     Double checkBalance(@PathVariable("walletId") String walletId);

    @PutMapping("/credit/{walletId}/{amount}")
    ResponseEntity<Double> creditAmount(@PathVariable String walletId, @PathVariable Double amount);

    @PutMapping("/debit/{walletId}/{amount}")
     void debitAmount(@PathVariable("walletId") String walletId, @PathVariable("amount") Double amount);
}
