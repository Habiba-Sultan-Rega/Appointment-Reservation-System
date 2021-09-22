package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Provider")
public class Provider extends User {
    private String organizationName;

    public Provider(String orgName) {
        this.organizationName = orgName;
    }
}
