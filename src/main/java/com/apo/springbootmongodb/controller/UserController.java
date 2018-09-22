package com.apo.springbootmongodb.controller;

import com.apo.springbootmongodb.Service.UserService;
import com.apo.springbootmongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        List<User> list = userService.getAllUsers();
        return list;
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUserById(id);
    }
}
