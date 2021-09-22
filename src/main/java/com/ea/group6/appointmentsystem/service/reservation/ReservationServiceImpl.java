package com.ea.group6.appointmentsystem.service.reservation;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.exception.MultipleAcceptedReservationNotAllowed;
import com.ea.group6.appointmentsystem.exception.ResourceNotFoundException;
import com.ea.group6.appointmentsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
        private ReservationRepository reservationRepository;

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

    @Override
    public Reservation approveReservation(User user, Long id, String status) {

            Reservation reservation = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", String.format("entity with id %s is NOT created previously",id)));

            Reservation reservationUpdated = null;

            if(reservation != null){
                if("ACCEPTED".equals(status)){
                    Appointment appointment = reservation.getAppointment();
                    long countOfAccepted = findAllReservationsGivenAppointmentId(reservation.getId())
                            .stream()
                            .filter(reserve -> reserve.getStatus().equals(Status.ACCEPTED))
                            .count();
                    if(countOfAccepted == 0){
                        reservation.setStatus(Status.ACCEPTED);
                        reservation.setProvider((Provider) user);// the Provider
                        reservation.setApprovalDate(LocalDate.now());
                        reservation.setApprovalTime(LocalTime.now());
                        reservationUpdated = reservationRepository.save(reservation);
                    }
                }else if("DECLINED".equals(status)){
                    reservation.setStatus(Status.DECLINED);
                    reservation.setProvider((Provider) user);// the Provider
                    reservation.setApprovalDate(LocalDate.now());
                    reservation.setApprovalTime(LocalTime.now());
                    reservationUpdated = reservationRepository.save(reservation);
                }
            }else{
                throw new MultipleAcceptedReservationNotAllowed("You cannot approve reservation of the same appointment as \"ACCEPTED\" status more than once!");
            }
        return reservationUpdated;
    }

    @Override
    public List<Reservation> findAllReservationsGivenAppointmentId(Long id) {
        return reservationRepository.findAllReservationsGivenAppointmentId(id);
    }
}
