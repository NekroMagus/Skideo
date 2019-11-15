package com.skideo.service;

import com.skideo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findByLogin(String login);

    List<User> findAll();
}