package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Provider")
public class Provider extends User {
    @Id
    @GeneratedValue
    private Long id;
    private String organizationName;

    public Provider(String orgName) {
        this.organizationName = orgName;
    }
}
