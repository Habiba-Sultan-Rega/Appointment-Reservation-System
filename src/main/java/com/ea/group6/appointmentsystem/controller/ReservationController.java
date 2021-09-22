package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Reservation;
import com.ea.group6.appointmentsystem.domain.Status;
import com.ea.group6.appointmentsystem.dto.ReservationDTO;
import com.ea.group6.appointmentsystem.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public void save(@Valid @RequestBody ReservationDTO reservationDTO){
        reservationService.save(makeReservation(reservationDTO));
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable(name = "id") Long id, @Valid @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long id, @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        reservationService.delete(reservation);
    }
    @PatchMapping("/{status}")
    public ResponseEntity<?> approveReservation(@RequestBody Reservation reservation, @PathVariable("id") String status) {
        Reservation reservation1 = reservationService.approveReservation(reservation, status);
        if(reservation1 == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        else
            return ResponseEntity.ok("Reservation approved!");
    }

    private Reservation makeReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
            reservation.setClient(reservationDTO.getClient());
            reservation.setAppointment(reservationDTO.getAppointment());
            reservation.setAppointmentReason(reservationDTO.getAppointmentReason());
            reservation.setApprovalDate(LocalDate.now());
            reservation.setApprovalTime(LocalTime.now());
            reservation.setStatus(Status.PENDING);
        return reservation;
    }
}