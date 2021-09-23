package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select *, 0 AS clazz_  from User where username = :username LIMIT 1", nativeQuery = true)
    public User findUserByUsername(@Param("username") String username);

    @Query(value = "Select *, 0 AS clazz_ from User where username = :username and password = :password LIMIT 1", nativeQuery = true)
    public User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT distinct r.roleName FROM User u INNER JOIN userRole ur On (u.id = ur.user_id) Inner Join " +
            " Role r on (ur.role_id = r.id) where u.id = :id", nativeQuery = true)
    public List<String> findUserRolesByUserId(@Param("id") Long id);
}
