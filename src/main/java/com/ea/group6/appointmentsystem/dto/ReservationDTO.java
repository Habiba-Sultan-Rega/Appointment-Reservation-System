package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private LocalDate approvalDate;

    private LocalTime approvalTime;

    private Status status;

    private ReservationType reservationType;

    private String appointmentReason;

    private Provider provider;

    private Client client;

    private Appointment appointment;
}
