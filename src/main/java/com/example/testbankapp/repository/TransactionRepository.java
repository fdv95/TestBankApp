package com.example.testbankapp.repository;


import com.example.testbankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_AccountNumber(Long accountNumber);
}
