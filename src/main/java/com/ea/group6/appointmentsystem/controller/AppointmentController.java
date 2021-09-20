package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    AppointmentService appointmentService;
    
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> findAll(){
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> findById(@PathVariable Long id){
        return appointmentService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Appointment appointment){
        appointmentService.save(appointment);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable(name = "id") Long id, @RequestBody Appointment appointment) {
        appointmentService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return appointmentService.update(appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long appointmentId, @RequestBody Appointment appointment) {
        appointmentService.findById(appointmentId).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        appointmentService.delete(appointment);
    }
}