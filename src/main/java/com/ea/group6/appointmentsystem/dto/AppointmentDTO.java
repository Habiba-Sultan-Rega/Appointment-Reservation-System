package com.ea.group6.appointmentsystem.dto;

import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    @FutureOrPresent
    private LocalDate date;
    @FutureOrPresent
    private LocalTime time;
    @NotNull
    private String location;
    @NotNull
    private String duration;
    private Provider provider;
    private Category category;
}
