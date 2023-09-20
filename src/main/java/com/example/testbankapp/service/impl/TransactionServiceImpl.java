package com.example.testbankapp.service.impl;


import com.example.testbankapp.entity.Transaction;
import com.example.testbankapp.repository.TransactionRepository;
import com.example.testbankapp.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions(String name) {
        return null;
    }
}
