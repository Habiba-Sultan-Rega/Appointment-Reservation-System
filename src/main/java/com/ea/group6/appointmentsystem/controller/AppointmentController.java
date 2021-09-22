package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Provider;
import com.ea.group6.appointmentsystem.dto.AppointmentDTO;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
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

    //ADDED VALIDATION
    @PostMapping
    public void save(@Valid @RequestBody AppointmentDTO appointmentDTO){
        appointmentService.save(makeAppointment(appointmentDTO));
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable(name = "id") Long id, @Valid @RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.findById(id).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return appointmentService.update(makeAppointment(appointmentDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long appointmentId, @RequestBody Appointment appointment) {
        appointmentService.findById(appointmentId).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        appointmentService.delete(appointment);
    }

    private Appointment makeAppointment(AppointmentDTO appointmentDTO){
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setLocation(appointmentDTO.getLocation());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setProvider(appointmentDTO.getProvider());
        appointment.setCategory(appointmentDTO.getCategory());
        return appointment;
    }

}