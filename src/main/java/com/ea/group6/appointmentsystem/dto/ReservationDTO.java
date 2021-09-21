package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String appointmentReason;
    private Client client;
    private Appointment appointment;
}
