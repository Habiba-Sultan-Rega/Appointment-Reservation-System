package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select * from Reservation where appointment_id = :appointment_id", nativeQuery = true)
    public List<Reservation> findAllReservationsGivenAppointmentId(@Param("appointment_id") Long id);

    //@Query("SELECT distinct r. FROM Reservation  JOIN  f.reservations a " +
           // "WHERE a.status ='ACCEPTED' and f.date=:date ")
    //Optional<List<Reservation>> getReservationsForReminder(@Param("date") LocalDateTime date);
//reservation.getStatus()
//                reservation.getAppointment().getDate()
    @Query(" from Reservation r JOIN  r.appointment a WHERE r.status ='ACCEPTED' and a.date=:date ")
    Optional<List<Reservation>> getReservationsForReminder(@Param("date") LocalDate date);
}
