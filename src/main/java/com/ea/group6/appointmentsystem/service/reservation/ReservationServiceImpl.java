package com.ea.group6.appointmentsystem.service.reservation;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.repository.ReservationRepository;
import com.ea.group6.appointmentsystem.service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

        ReservationRepository reservationRepository;

        @Autowired
        private EmailService mailService;

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
    public Reservation approveReservation(Reservation reservation, String status) {
            Reservation reservation1= findById(reservation.getId()).get();//first, check if the record is in the DB
            Reservation reservationUpdated = null;
            if(reservation1 != null){
                if("ACCEPTED".equals(status)){
                    Appointment appointment = reservation.getAppointment();
                    long countOfAccepted = appointment.getReservations()
                            .stream()
                            .filter(reserve -> reserve.getClient().equals(Status.ACCEPTED))
                            .count();
                    if(countOfAccepted == 0){
                        reservation.setStatus(Status.valueOf(status));
                       // reservation.setProvider((Provider) user);
                        reservation.setApprovalDate(LocalDate.now());
                        reservation.setApprovalTime(LocalTime.now());

                        reservationUpdated = reservationRepository.save(reservation);
                        String message = "Dear "+ reservation.getClient()+
                                ", /n We are pleased to let " +
                                "you know that your reservation has been approved on "
                                + reservation.getAppointment().getDate()+
                                " /n "+"Best Regards, /n"
                                +reservation.getProvider();
                      if(reservation.getAppointment().getDate().plusDays(1).equals(LocalDate.now()))
                      mailService.sendEmailFromService("hrega@miu.edu","Reservation has been ACCEPTED",message);

                    }
                }else if("DECLINED".equals(status)){
                    reservation.setStatus(Status.valueOf(status));
                    reservationUpdated = reservationRepository.save(reservation);
                    String message = "Dear "+ reservation.getClient()+
                            ", /n We are sorry to tell " +
                            "you that your reservation has been Declined on "
                            + reservation.getAppointment().getDate()+" please make another reservation."
                            + " /n "+"Best Regards, /n"
                            + reservation.getProvider().getRoleName();

                    mailService.sendEmailFromService("hrega@miu.edu","Reservation has been ACCEPTED",message);
                }
            }
        return reservationUpdated;

    }

    @Override
    public List<Reservation> findAllReservationsGivenAppointmentId(Long id) {
        return reservationRepository.findAllReservationsGivenAppointmentId(id);
    }

    @Override
    public void sendReservationReminder() {
        System.out.println("Finished putting the email in the queue");
    }
}
