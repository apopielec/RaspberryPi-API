package com.apo.springbootmongodb.security;

import com.apo.springbootmongodb.service.AppUserService;
import com.apo.springbootmongodb.service.impl.AppUserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final AppUserDetailsServiceImpl appUserDetailsService;

    public SecurityConfig(AppUserDetailsServiceImpl appUserService){
        this.appUserDetailsService = appUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/events/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/app/**").hasAnyRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), appUserDetailsService));
    }
}
