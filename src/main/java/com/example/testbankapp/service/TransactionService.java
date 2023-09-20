package com.example.testbankapp.service;


import com.example.testbankapp.entity.Transaction;

import java.util.List;

public interface TransactionService {

    void save(Transaction transaction);

    List<Transaction> findAllTransactionByAccountNumber(Long accountNumber);
}
