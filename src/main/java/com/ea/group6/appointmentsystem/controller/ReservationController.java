package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.domain.Reservation;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentService;
import com.ea.group6.appointmentsystem.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> findAll(){
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> findById(@PathVariable Long id){
        return reservationService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Reservation reservation){
        reservationService.save(reservation);
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable(name = "id") Long id, @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long id, @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        reservationService.delete(reservation);
    }
}