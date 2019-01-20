package com.apo.springbootmongodb.security;

import com.apo.springbootmongodb.model.AppUser;
import com.apo.springbootmongodb.service.AppUserService;
import com.apo.springbootmongodb.service.impl.AppUserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.apo.springbootmongodb.security.SecurityUtilities.HEADER_STRING;
import static com.apo.springbootmongodb.security.SecurityUtilities.SECRET;
import static com.apo.springbootmongodb.security.SecurityUtilities.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final AppUserDetailsServiceImpl appUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AppUserDetailsServiceImpl appUserDetails) {
        super(authenticationManager);
        this.appUserDetailsService = appUserDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernameCredentials = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernameCredentials);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        UserDetails appUserDetails = appUserDetailsService.loadUserByUsername(username);

        if(appUserDetails == null)
            return null;

        return new UsernamePasswordAuthenticationToken(appUserDetails, null, appUserDetails.getAuthorities());
    }
}
