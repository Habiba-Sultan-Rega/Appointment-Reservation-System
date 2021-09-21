package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private LocalDate date;

    private LocalTime time;

    private String location;

    private String duration;

    private Provider provider;

    private Category category;
}
