package com.example.testbankapp.repository;


import com.example.testbankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long accountNumber);
}
