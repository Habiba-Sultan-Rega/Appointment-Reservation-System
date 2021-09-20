package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
