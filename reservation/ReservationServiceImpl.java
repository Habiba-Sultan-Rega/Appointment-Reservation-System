package edu.mum.apointementsystem.service.reservation;

import edu.mum.apointementsystem.exception.NoReservationException;
import edu.mum.apointementsystem.exception.PastReservationException;
import edu.mum.apointementsystem.model.Appointment;
import edu.mum.apointementsystem.model.Reservation;
import edu.mum.apointementsystem.model.Role;
import edu.mum.apointementsystem.model.RoleType;
import edu.mum.apointementsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.mum.apointementsystem.model.AppointmentStatus.APPROVED;

public class ReservationServiceImpl implements ReservationService{


    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation creatReservation(Reservation reservation) {
        reservationRepository.findById ( reservation.getId ()).ifPresent(s ->{
            throw new RuntimeException ("Already exists");

        });

        if(reservation.getDate ().isEqual ( LocalDate.now () ) || reservation.getDate ().isBefore ( LocalDate.now () ) )
            throw new RuntimeException ("Local date");
        if(reservation.getProvider () == null || !reservation.getProvider ().getRoles ().contains ( new Role ( RoleType.PROVIDER ) ))
            throw new RuntimeException ("No Provider");

        return reservationRepository.save ( reservation );
    }

    @Override
    public Reservation deleteReservation(Reservation reservation) {
        Reservation reservation1 = reservationRepository.findById(reservation.getId()).orElseThrow(RuntimeException::new);
        if (reservation1.getId() == reservation.getId()) {
            reservationRepository.delete(reservation);
        }
        return reservation;
    }

    public Reservation updateReservation(Reservation reservation) {
        Reservation reservation1 = reservationRepository.findById(reservation.getId()).orElseThrow(RuntimeException::new);
        if(reservation1.getDate().isBefore(LocalDate.now()))
            throw new PastReservationException ();
        if (reservation1.getId() == reservation.getId()) {
            if(reservation.getProvider() == null) reservation.setProvider(reservation.getProvider());
            if(reservation.getDate() == null) reservation.setDate(reservation1.getDate());
            if(reservation.getStartTime() == null) reservation.setStartTime(reservation1.getStartTime());
            if(reservation.getDuration() == 0) reservation.setDuration(reservation1.getDuration());
            if(reservation.getLocation() == null) reservation.setLocation(reservation1.getLocation());
            reservationRepository.save(reservation);
        } else {
            throw new NoReservationException ();
        }
        return reservation;
    }


    @Override
    public List<Reservation> getAllReservation() {

        return reservationRepository.findAll ();
    }

    @Override
    public List<Reservation> getAllAvailableReservation() {
        return
                reservationRepository.findAll ()
                .stream ().filter ( reservation -> reservation.getDate ().isAfter ( LocalDate.now () )||
                        (reservation.getDate ().isEqual ( LocalDate.now () ) && reservation.getStartTime ().isAfter ( LocalTime.now () )) )
                .filter ( reservation -> {
                    for(Appointment appointment : reservation.getAppointments ())
                        if(appointment.getStatus ()==APPROVED) return false;
                        return true;
                } ).collect( Collectors.toList());
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.findById ( id ).orElseThrow (NoReservationException::new);
    }


}
