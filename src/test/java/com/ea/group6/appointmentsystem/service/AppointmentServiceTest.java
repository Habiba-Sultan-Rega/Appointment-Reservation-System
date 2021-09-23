package com.ea.group6.appointmentsystem.service;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.repository.AppointmentRepository;
import com.ea.group6.appointmentsystem.repository.ProviderRepository;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;


   @Mock
    private AppointmentServiceImpl appointmentService;


    @BeforeEach
    void setUp() {


        appointmentService = new AppointmentServiceImpl(appointmentRepository);

    }

    @Test
    void canGetOneAppointmentById() {
        Status s1 = Status.ACCEPTED;
        Status s2 = Status.ACCEPTED;
        ReservationType restype1 = ReservationType.EXPEDIT;
        ReservationType restype2 = ReservationType.EXPEDIT;
        LocalDate dt = LocalDate.parse("2018-11-01");

        LocalTime tm = LocalTime.parse("12:00");
        Provider provider1 = new Provider("MIU Clinic");
        Provider provider2 = new Provider("MIU TM office");
        Client client1 = new Client();

        Reservation res1 = new Reservation(s1,dt,tm,restype1, "to see u",provider1, client1, null);
        Reservation res2 = new Reservation(s2,dt,tm,restype2, "to see u",provider2, client1, null);

        Category category2 = new Category("TM", "30 mins", null);

        List<Reservation> reserveList = new ArrayList<>();

        reserveList.add(res1);
        reserveList.add(res2);
        Appointment appointment1 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );
        Appointment appointment2 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );

        List<Appointment> appoints = new ArrayList<>();
        appoints.add(appointment1);
        appoints.add(appointment2);

        category2.setAppointmentList(appoints);


        when(appointmentRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.ofNullable(appointment1));
        appointmentService.findById(2L);

        // assertion
        verify(appointmentRepository).findById(2L);


    }

 @Test
    void canAddAppointment() throws ParseException {
     Status s1 = Status.ACCEPTED;
     Status s2 = Status.ACCEPTED;
     ReservationType restype1 = ReservationType.EXPEDIT;
     ReservationType restype2 = ReservationType.EXPEDIT;
     LocalDate dt = LocalDate.parse("2018-11-01");

     LocalTime tm = LocalTime.parse("12:00");
     Provider provider1 = new Provider("MIU Clinic");
     Provider provider2 = new Provider("MIU TM office");
     Client client1 = new Client();

     Reservation res1 = new Reservation(s1,dt,tm,restype1, "to see u",provider1, client1, null);
     Reservation res2 = new Reservation(s2,dt,tm,restype2, "to see u",provider2, client1, null);

     Category category2 = new Category("TM", "30 mins", null);

     List<Reservation> reserveList = new ArrayList<>();

     reserveList.add(res1);
     reserveList.add(res2);
     Appointment appointment1 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );
     Appointment appointment2 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );

     List<Appointment> appoints = new ArrayList<>();
     appoints.add(appointment1);
     appoints.add(appointment2);

     category2.setAppointmentList(appoints);


   appointmentService.save(appointment2);

     //assertion
    assertEquals(provider1, appointment2.getProvider());
    verify(appointmentRepository).save(appointment2);

}


    @Test
    void canDelete() {

        Status s1 = Status.ACCEPTED;
        Status s2 = Status.ACCEPTED;
        ReservationType restype1 = ReservationType.EXPEDIT;
        ReservationType restype2 = ReservationType.EXPEDIT;
        LocalDate dt = LocalDate.parse("2018-11-01");

        LocalTime tm = LocalTime.parse("12:00");
        Provider provider1 = new Provider("MIU Clinic");
        Provider provider2 = new Provider("MIU TM office");
        Client client1 = new Client();

        Reservation res1 = new Reservation(s1,dt,tm,restype1, "to see u",provider1, client1, null);
        Reservation res2 = new Reservation(s2,dt,tm,restype2, "to see u",provider2, client1, null);

        Category category2 = new Category("TM", "30 mins", null);

        List<Reservation> reserveList = new ArrayList<>();

        reserveList.add(res1);
        reserveList.add(res2);
        Appointment appointment1 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );
        Appointment appointment2 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );

        List<Appointment> appoints = new ArrayList<>();
        appoints.add(appointment1);
        appoints.add(appointment2);

        category2.setAppointmentList(appoints);

        Appointment expected = new Appointment();
        appointmentService.delete(expected);
        Mockito.verify(appointmentRepository).delete(expected);
    }

    @Test
    void canGetAllAppointments(){
        Status s1 = Status.ACCEPTED;
        Status s2 = Status.ACCEPTED;
        ReservationType restype1 = ReservationType.EXPEDIT;
        ReservationType restype2 = ReservationType.EXPEDIT;
        LocalDate dt = LocalDate.parse("2018-11-01");

        LocalTime tm = LocalTime.parse("12:00");
        Provider provider1 = new Provider("MIU Clinic");
        Provider provider2 = new Provider("MIU TM office");
        Client client1 = new Client();

        Reservation res1 = new Reservation(s1,dt,tm,restype1, "to see u",provider1, client1, null);
        Reservation res2 = new Reservation(s2,dt,tm,restype2, "to see u",provider2, client1, null);

        Category category2 = new Category("TM", "30 mins", null);

        List<Reservation> reserveList = new ArrayList<>();

        reserveList.add(res1);
        reserveList.add(res2);
        Appointment appointment1 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );
        Appointment appointment2 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );

        List<Appointment> appoints = new ArrayList<>();
        appoints.add(appointment1);
        appoints.add(appointment2);

        category2.setAppointmentList(appoints);


        when(appointmentRepository.findAll()).thenReturn(appoints);

        List<Appointment> actualAppointments = appointmentService.findAll();
        assertNotNull(actualAppointments);

        assertEquals(2, actualAppointments.size());
    }

}
