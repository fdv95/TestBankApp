package com.example.testbankapp.service;


import com.example.testbankapp.dto.AccountDto;
import com.example.testbankapp.dto.DepositDto;
import com.example.testbankapp.dto.TransferDto;
import com.example.testbankapp.entity.Account;
import com.example.testbankapp.entity.Transaction;

import java.util.List;

public interface AccountService {

    Account createAccount(AccountDto accountDto);

    Transaction deposit(DepositDto depositDto);
    Transaction withdraw(DepositDto depositDto);
    Transaction transfer(TransferDto transferDto);
}
