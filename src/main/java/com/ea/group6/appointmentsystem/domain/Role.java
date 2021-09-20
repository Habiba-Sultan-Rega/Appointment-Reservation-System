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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}