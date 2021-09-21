package edu.mum.apointementsystem.service.reservation;

import edu.mum.apointementsystem.model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationService  {
    public Reservation creatReservation(Reservation reservation);
    public Reservation deleteReservation(Reservation reservation);
    public Reservation updateReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public List<Reservation>getAllAvailableReservation();
    public Reservation getReservationById(int id);




}
