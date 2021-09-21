package com.ea.group6.appointmentsystem.security.filter;

import com.ea.group6.appointmentsystem.security.JWTAuthentication;
import com.ea.group6.appointmentsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("authenticating request at "+request.getRequestURL());
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            logger.trace("Request with jwt token");
            try
            {
                Authentication authentication = authenticationManager.authenticate(new JWTAuthentication(authorizationHeader.substring(7)));
                if(authentication.isAuthenticated()){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request,response);
                }
                else
                {
                    logger.warn("Invalid authentication attempt");
                    throw new BadCredentialsException("Invalid jwt token");
                }
            }
            catch (AuthenticationException e)
            {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        else{
            filterChain.doFilter(request,response);
        }
    }
}
