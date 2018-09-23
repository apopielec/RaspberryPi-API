package com.apo.springbootmongodb.service;

import com.apo.springbootmongodb.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void deleteUserById(String id);
    User getUserById(String id);
    List<User> getAllUsers();
    List<String> getUsersUpcomingEvent();
}
