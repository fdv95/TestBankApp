package com.example.testbankapp.controller;


import com.example.testbankapp.dto.AccountDto;
import com.example.testbankapp.dto.DepositDto;
import com.example.testbankapp.dto.TransferDto;
import com.example.testbankapp.entity.Account;
import com.example.testbankapp.entity.Transaction;
import com.example.testbankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestBody @Valid AccountDto accountDto) {
        return new ResponseEntity<>(service.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PutMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositDto depositDto) {
        return ResponseEntity.ok(service.deposit(depositDto));
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody DepositDto depositDto) {
        return ResponseEntity.ok(service.withdraw(depositDto));
    }

    @PutMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(service.transfer(transferDto));
    }
}
