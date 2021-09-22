package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    @NotNull
    @Size(min=5, max=100)
    private String appointmentReason;
    private Client client;
    private Appointment appointment;
}
