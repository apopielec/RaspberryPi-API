package com.apo.springbootmongodb.service;

import com.apo.springbootmongodb.model.AppUser;

import java.util.List;

public interface AppUserService {

    void save(AppUser appUser);
    List<AppUser> findAll();
    AppUser findByUsername(String username);
}
