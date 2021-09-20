package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Admin extends Role{
    private String qualification;

    public Admin(String qualification) {
        this.qualification = qualification;
    }
}
