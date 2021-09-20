package com.ea.group6.appointmentsystem.service.reservation;

import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    void delete(Reservation reservation);

    Reservation update(Reservation reservation);

    void save(Reservation reservation);
}
