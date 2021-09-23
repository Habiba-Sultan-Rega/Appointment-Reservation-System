package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Admin")
public class Admin extends User{
    private String qualification;

    public Admin(String qualification) {
        this.qualification = qualification;
    }
}
