package com.apo.springbootmongodb.service.impl;

import com.apo.springbootmongodb.model.AppUser;
import com.apo.springbootmongodb.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final AppUserService appUserService;

    @Autowired
    public AppUserDetailsServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByUsername(username);

        if(appUser == null)
            throw new UsernameNotFoundException(username);

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return new User(appUser.getUsername(), encoder.encode(appUser.getPassword()), AuthorityUtils.createAuthorityList("ROLE_"+appUser.getRole().toUpperCase()));
    }
}
