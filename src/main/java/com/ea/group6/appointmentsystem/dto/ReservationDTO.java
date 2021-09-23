package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private LocalDate approvalDate;

    private LocalTime approvalTime;

    private Status status;

    private ReservationType reservationType;

    @NotNull
    @Size(min=5, max=100)

    private String appointmentReason;

    private Provider provider;

    private Client client;

    private Appointment appointment;
}
