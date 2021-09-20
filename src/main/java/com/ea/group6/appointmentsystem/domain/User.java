package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SecondaryTable(name = "Credential")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;

    @Column(table = "Credential")
    private String username;

    @Column(table = "Credential")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    public User(String firstName, String lastName, String emailAddress, String phone, String username, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
