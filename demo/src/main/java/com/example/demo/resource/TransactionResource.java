package com.example.demo.resource;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionResource {
    private final TransactionService transactionService;

    @PostMapping("/add")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @PostMapping(path = "/withdrawal")
    public void debit(@RequestBody Transaction request) {
        String walletId = request.getWalletId();
        Double amount = request.getAmount();
        transactionService.debit(walletId, amount);
    }

    @PostMapping(path = "/deposit")
    public void credier(@RequestBody Transaction request) {
        String walletId = request.getWalletId();
        Double amount = request.getAmount();
        transactionService.credit(walletId, amount);
    }
}
