package com.ea.group6.appointmentsystem.service.reservation;

import com.ea.group6.appointmentsystem.domain.Reservation;
import com.ea.group6.appointmentsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

        @Override
        public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

        @Override
        public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

        @Override
        public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

        @Override
        public Reservation update(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}
