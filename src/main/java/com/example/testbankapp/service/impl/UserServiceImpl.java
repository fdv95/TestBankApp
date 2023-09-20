package com.example.testbankapp.service.impl;

import com.example.testbankapp.entity.User;
import com.example.testbankapp.repository.UserRepository;
import com.example.testbankapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findByName(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Пользователь не найден")
        );
    }

    @Override
    @Transactional
    public void save(User user) {
        repository.save(user);
    }
}
