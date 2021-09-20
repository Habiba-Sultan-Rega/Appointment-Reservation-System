package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
