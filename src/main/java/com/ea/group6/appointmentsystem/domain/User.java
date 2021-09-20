package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Gender gender;

    @Column(table = "Credential")
    private String username;

    @Column(table = "Credential")
    private String password;

    public User(String firstName, String lastName, String emailAddress, String phone, Gender gender, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }
}
