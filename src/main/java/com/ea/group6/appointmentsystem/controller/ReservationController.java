package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Reservation;
import com.ea.group6.appointmentsystem.domain.Status;
import com.ea.group6.appointmentsystem.domain.User;
import com.ea.group6.appointmentsystem.dto.ReservationDTO;
import com.ea.group6.appointmentsystem.service.reservation.ReservationService;
import com.ea.group6.appointmentsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;
    private UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long id, @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        reservationService.delete(reservation);
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable(name = "id") Long id, @Valid @RequestBody Reservation reservation) {
        reservationService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return reservationService.update(reservation);
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<?> approveReservation(@PathVariable("id") Long id, @PathVariable("status") String status) {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = principal.toString();
        User user = userService.findUserByUsername(username);

        Reservation reservation = reservationService.approveReservation(user, id, status);
        if(reservation == null)
            return ResponseEntity.badRequest().body("CAUTION:- You cannot approve as \"ACCEPTED\" more than once!");
        else
            return ResponseEntity.ok("Reservation approved!");
    }

    private Reservation makeReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
            reservation.setClient(reservationDTO.getClient());
            reservation.setAppointment(reservationDTO.getAppointment());
            reservation.setAppointmentReason(reservationDTO.getAppointmentReason());
            reservation.setReservationType(reservationDTO.getReservationType());
            reservation.setStatus(Status.PENDING);
        return reservation;
    }
}