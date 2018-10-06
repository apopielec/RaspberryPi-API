package com.apo.springbootmongodb.controller;

import com.apo.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final UserService userService;

    @Autowired
    public EventController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/upcoming")
    public List<String> getUsersWithUpcomingEvent(){
        return userService.getUsersUpcomingEvent();
    }
}
