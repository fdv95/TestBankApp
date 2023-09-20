package com.example.testbankapp.controller;

import com.example.testbankapp.entity.Transaction;
import com.example.testbankapp.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> findTransactionByAccountNumber(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(service.findAllTransactionByAccountNumber(accountNumber));
    }
}
