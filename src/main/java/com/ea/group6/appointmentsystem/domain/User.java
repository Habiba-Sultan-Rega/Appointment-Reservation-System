package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String emailAddress;
    private String phone;

    @Enumerated(EnumType.STRING)
    @Value("FEMALE")
    private Gender gender;

    @NotNull
    private String username;
    @NotNull
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
