package com.example.demo.service;

import com.example.demo.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Transaction createTransaction(Transaction transaction) ;
    Transaction getTransaction(Long id);
    Transaction updateTransaction(Long id, Transaction transaction);
     void deleteTransaction(Long id);

     void credit(String walletId, Double amount) ;
    void debit(String walletId, Double amount);
}
