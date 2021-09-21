package com.ea.group6.appointmentsystem.service.user;

import com.ea.group6.appointmentsystem.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    void delete(User user);

    User update(User user);

    void save(User user);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

    List<String> findUserRolesByUserId(Long id);
}
