package com.apo.springbootmongodb.security;

public class SecurityUtilities {

    public static final String SECRET               = "SECRET";
    public static final String TOKEN_PREFIX         = "Bearer ";
    public static final String HEADER_STRING        = "Authorization";
    public static final long EXPIRATION_TIME_IN_SEC = 259200000;    // 30 days
}
