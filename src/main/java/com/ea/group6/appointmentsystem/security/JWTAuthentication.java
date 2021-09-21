package com.ea.group6.appointmentsystem.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication extends UsernamePasswordAuthenticationToken{
    public JWTAuthentication(Object credentials){
        super(null, credentials);
    }

    public JWTAuthentication(Object credentials, Collection<? extends GrantedAuthority> authorities){
        super(null, credentials, authorities);
    }
}
