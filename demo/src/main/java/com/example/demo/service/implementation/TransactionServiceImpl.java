package com.example.demo.service.implementation;

import com.example.demo.client.WalletClient;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.Type;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
private final TransactionRepository transactionRepository;
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    @Autowired
    private WalletClient walletClient;
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = getTransaction(id);
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setType(transaction.getType());
        existingTransaction.setDate(transaction.getDate());
        return transactionRepository.save(existingTransaction);
    }
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public void debit(String walletId, Double amount) {
        Double balance = walletClient.checkBalance(walletId);
        if (balance >= amount) {
            walletClient.debitAmount(walletId, amount);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setType(Type.withdrawal);
            transaction.setWalletId(walletId);
            transactionRepository.save(transaction);
        } else {
            throw new IllegalStateException("Votre solde est insuffisant");
        }
    }

    public void credit(String walletId, Double amount) {
        walletClient.creditAmount(walletId, amount);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(Type.deposit);
        transaction.setWalletId(walletId);
        transactionRepository.save(transaction);
    }
}
