package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Provider extends Role {
    @Id
    @GeneratedValue
    private Long id;
    private String organizationName;

    public Provider(String orgName) {
        this.organizationName = orgName;
    }
}
