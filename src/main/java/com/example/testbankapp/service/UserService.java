package com.example.testbankapp.service;


import com.example.testbankapp.entity.User;

public interface UserService {
    User findByName(String name);

    void save(User user);
}
