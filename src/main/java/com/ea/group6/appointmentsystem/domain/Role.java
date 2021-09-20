package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String roleName;

    @ManyToMany
    @JoinTable
    private List<User> users;

    public Role(String roleName, List<User> users) {
        this.roleName = roleName;
        this.users = users;
    }
}