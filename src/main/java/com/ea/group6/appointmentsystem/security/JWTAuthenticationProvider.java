package com.ea.group6.appointmentsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider{
    private LoginUserDetailsService loginUserDetailsService;
    private Logger logger= LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    @Value("${appointment_app.security.jwt.key}")
    private String key;

    @Autowired
    public JWTAuthenticationProvider(LoginUserDetailsService loginUserDetailsService){
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String jwtToken = authentication.getCredentials().toString();
        try{
            logger.info("validating jwt token "+jwtToken);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key.getBytes())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            UserDetails userDetails = loginUserDetailsService.loadUserByUsername(claims.get("username").toString());
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        }
        catch (JwtException j){
            logger.warn("Invalid jwt toke attempt. jwt:"+jwtToken);
            throw new BadCredentialsException("Invalid jwtToken ");
        }
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.isAssignableFrom(JWTAuthentication.class);
    }
}
