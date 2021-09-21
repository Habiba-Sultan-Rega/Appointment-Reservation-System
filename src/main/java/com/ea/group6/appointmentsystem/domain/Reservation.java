package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate approvalDate;
    private LocalTime approvalTime;

    @Enumerated(EnumType.STRING)
    @Value("PENDING")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Value("ORDINARY")
    private ReservationType reservationType;

    @Column(name = "reason")
    private String appointmentReason;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;

    public Reservation(Status status, LocalDate approvalDate, LocalTime approvalTime, ReservationType reservationType, String appointmentReason, Provider provider, Client client, Appointment appointment) {
        this.status = status;
        this.approvalDate = approvalDate;
        this.approvalTime = approvalTime;
        this.reservationType = reservationType;
        this.appointmentReason = appointmentReason;
        this.provider = provider;
        this.client = client;
        this.appointment = appointment;
    }
}
