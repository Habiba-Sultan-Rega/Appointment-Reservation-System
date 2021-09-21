package edu.mum.apointementsystem.repository;

import edu.mum.apointementsystem.model.Appointment;
import edu.mum.apointementsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation , Integer> {


}
