package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private LocalTime time;

    private String location;

    private String duration; //to override the default duration value in Category

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany (mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Appointment(LocalDate date, LocalTime time, String location, Provider provider, List<Reservation> reservations) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.provider = provider;
        this.reservations = reservations;
    }
}