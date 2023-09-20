package com.example.testbankapp.service.impl;

import com.example.testbankapp.dto.AccountDto;
import com.example.testbankapp.dto.DepositDto;
import com.example.testbankapp.dto.TransferDto;
import com.example.testbankapp.entity.Account;
import com.example.testbankapp.repository.AccountRepository;
import com.example.testbankapp.repository.TransactionRepository;
import com.example.testbankapp.repository.UserRepository;
import com.example.testbankapp.service.AccountService;
import com.example.testbankapp.entity.User;
import com.example.testbankapp.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) {

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .pinCode(accountDto.getPinCode())
                .balance(BigDecimal.ZERO)
                .build();

        String userName = accountDto.getUserDto().getName();

        Optional<User> optionalUser = userRepository.findByName(userName);
        User user;

        if (optionalUser.isEmpty()) {
            user = User.builder()
                    .name(userName)
                    .accounts(new ArrayList<>())
                    .build();

            account.setUser(user);
        } else {
            user = optionalUser.get();
            user.getAccounts().add(account);

            account.setUser(user);
        }

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Transaction deposit(DepositDto depositDto) { // pinCode, accountNumber, amount

        Account account = accountRepository.findByAccountNumber(depositDto.getAccountNumber()).orElseThrow(() ->
                new EntityNotFoundException("Аккакунт с указанным номером не найден")
        );

        if (account.getPinCode().equals(depositDto.getPinCode())) {
            account.setBalance(
                    account.getBalance().add(depositDto.getAmount())
            );
        } else {
            throw new IllegalArgumentException("Введен некорректный пин-код");
        }

        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .amount(depositDto.getAmount())
                .account(account)
                .build();

        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public Transaction withdraw(DepositDto depositDto) {
        Account account = accountRepository.findByAccountNumber(depositDto.getAccountNumber()).orElseThrow(() ->
                new EntityNotFoundException("Аккакунт с указанным номером не найден")
        );

        if (account.getPinCode().equals(depositDto.getPinCode())) {
            account.setBalance(
                    account.getBalance().subtract(depositDto.getAmount())
            );
        } else {
            throw new IllegalArgumentException("Введен некорректный пин-код");
        }

        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .amount(depositDto.getAmount())
//                .account(account)
                .build();

        transactionRepository.save(transaction);
        return transaction;
    }


    @Override
    @Transactional
    public Transaction transfer(TransferDto transferDto) {

        Account sourceAccount = accountRepository.findByAccountNumber(transferDto.getSourceAccountNumber()).orElseThrow(() ->
                new EntityNotFoundException("Аккакунт с указанным номером не найден")
        );

        Account targetAccount = accountRepository.findByAccountNumber(transferDto.getTargetAccountNumber()).orElseThrow(() ->
                new EntityNotFoundException("Аккакунт с указанным номером не найден")
        );

        if (sourceAccount.getPinCode().equals(transferDto.getPinCode())) {
            sourceAccount.setBalance(
                    sourceAccount.getBalance().subtract(transferDto.getAmount())
            );
            targetAccount.setBalance(
                    targetAccount.getBalance().add(transferDto.getAmount())
            );
        } else {
            throw new IllegalArgumentException("Введен некорректный пин-код");
        }


        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .amount(transferDto.getAmount())
//                .account(account)
                .build();

        transactionRepository.save(transaction);

        return transaction;
    }

    // todo генерирует 10-значный номер. Заменить на UUID?
    private Long generateAccountNumber() {
        long accountNumber = 0L;
        boolean isUnique = false;
        while (!isUnique) {
            Long number = 1_000_000_000L + new Random().nextLong(9_000_000_000L);
            Optional<Account> optionalAccount = accountRepository.findByAccountNumber(number);
            if (optionalAccount.isEmpty()) {
                accountNumber = number;
                isUnique = true;
            }
        }
        return accountNumber;
    }
}
