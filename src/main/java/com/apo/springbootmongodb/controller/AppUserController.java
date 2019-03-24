package com.apo.springbootmongodb.controller;

import com.apo.springbootmongodb.model.AppUser;
import com.apo.springbootmongodb.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/user")
    List<AppUser> getAllAppUsers(){
        return appUserService.findAll();
    }

    @GetMapping("/user/{username}")
    AppUser getAppUserByUsername(@PathVariable("username") String username){
        return appUserService.findByUsername(username);
    }

    @PostMapping("/user")
    void addAppUser(@RequestBody AppUser appUser){
        appUserService.save(appUser);
    }
}
