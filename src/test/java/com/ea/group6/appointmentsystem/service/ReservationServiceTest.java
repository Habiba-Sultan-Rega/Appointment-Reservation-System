package com.ea.group6.appointmentsystem.service;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.repository.AppointmentRepository;
import com.ea.group6.appointmentsystem.repository.ProviderRepository;
import com.ea.group6.appointmentsystem.repository.ReservationRepository;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentServiceImpl;
import com.ea.group6.appointmentsystem.service.reservation.ReservationService;
import com.ea.group6.appointmentsystem.service.reservation.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {

        reservationService = new ReservationServiceImpl(reservationRepository);
    }

    @Test
    void getAllReservationTest() {

        Status s1 = Status.ACCEPTED;
        Status s2 = Status.ACCEPTED;
        ReservationType restype1 = ReservationType.EXPEDIT;
        ReservationType restype2 = ReservationType.EXPEDIT;
        LocalDate dt = LocalDate.parse("2018-11-01");

        LocalTime tm = LocalTime.parse("12:00");
        Provider provider1 = new Provider("MIU Clinic");
        Provider provider2 = new Provider("MIU TM office");
        Client client1 = new Client();

        Reservation res1 = new Reservation(s1, dt, tm, restype1, "to see u", provider1, client1, null);
        Reservation res2 = new Reservation(s2, dt, tm, restype2, "to see u", provider2, client1, null);

        List<Reservation>res = new ArrayList<>();
        res.add(res1);
        res.add(res2);


        when(reservationRepository.findAll()).thenReturn(res);

        List<Reservation> actualReservations = reservationService.findAll();
        assertNotNull(actualReservations);

        assertEquals(2, actualReservations.size());

    }

    @Test
    void canSaveReservation(){

        Status s1 = Status.ACCEPTED;
        Status s2 = Status.ACCEPTED;
        ReservationType restype1 = ReservationType.EXPEDIT;
        ReservationType restype2 = ReservationType.EXPEDIT;
        LocalDate dt = LocalDate.parse("2018-11-01");

        LocalTime tm = LocalTime.parse("12:00");
        Provider provider1 = new Provider("MIU Clinic");
        Provider provider2 = new Provider("MIU TM office");
        Client client1 = new Client();

        Reservation res1 = new Reservation(s1, dt, tm, restype1, "to see u", provider1, client1, null);
        Reservation res2 = new Reservation(s2, dt, tm, restype2, "to see u", provider2, client1, null);

        List<Reservation>res = new ArrayList<>();
        res.add(res1);
        res.add(res2);

        reservationService.save(res1);

        //assertion
        assertEquals(provider1, res1.getProvider());
        verify(reservationRepository).save(res1);
    }

}
