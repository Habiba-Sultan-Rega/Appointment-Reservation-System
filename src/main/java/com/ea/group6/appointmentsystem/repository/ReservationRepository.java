package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select * from Reservation where appointment_id = :appointment_id", nativeQuery = true)
    public List<Reservation> findAllReservationsGivenAppointmentId(@Param("appointment_id") Long id);

}
