package com.ea.group6.appointmentsystem.service.appointment;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.repository.AppointmentRepository;
import com.ea.group6.appointmentsystem.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    AppointmentRepository appointmentRepository;
    ProviderRepository providerRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ProviderRepository providerRepository){
        this.appointmentRepository = appointmentRepository;
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
