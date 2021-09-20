package com.ea.group6.appointmentsystem.service.appointment;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> findAll();

    Optional<Appointment> findById(Long id);

    void delete(Appointment appointment);

    Appointment update(Appointment appointment);

    void save(Appointment appointment);
}
