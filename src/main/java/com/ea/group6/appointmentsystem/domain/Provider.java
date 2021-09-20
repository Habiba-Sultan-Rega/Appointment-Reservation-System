package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Provider extends Role {
    private String organizationName;

    public Provider(String orgName) {
        this.organizationName = orgName;
    }
}
