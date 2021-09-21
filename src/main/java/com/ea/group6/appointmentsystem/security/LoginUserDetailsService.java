package com.ea.group6.appointmentsystem.security;

import com.ea.group6.appointmentsystem.domain.User;
import com.ea.group6.appointmentsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class LoginUserDetailsService implements UserDetailsService{
    private final UserService userService;

    @Autowired
    public LoginUserDetailsService(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userService.findUserByUsername(username);
        if(user != null){
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
            return builder.password(user.getPassword()).authorities(userService.findUserRolesByUserId(user.getId())
                    .stream()
                    .map(roleName -> new SimpleGrantedAuthority(roleName))
                    .collect(Collectors.toList())).build();
        }
        else throw new UsernameNotFoundException("Username not found for user "+username);
    }
}
