package com.apo.springbootmongodb.Service.Impl;

import com.apo.springbootmongodb.Service.UserService;
import com.apo.springbootmongodb.model.User;
import com.apo.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = userRepository.findAll();
        return listUsers;
    }
}
