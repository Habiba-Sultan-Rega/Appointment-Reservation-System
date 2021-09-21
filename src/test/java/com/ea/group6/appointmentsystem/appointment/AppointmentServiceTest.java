package com.ea.group6.appointmentsystem.appointment;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.repository.AppointmentRepository;
import com.ea.group6.appointmentsystem.repository.ProviderRepository;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentService;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentServiceImpl;
import com.ea.group6.appointmentsystem.service.provider.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ProviderRepository providerRepository;

    //private Appointment appointment;
    //private Provider provider1;

    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {

        appointmentService = new AppointmentServiceImpl(appointmentRepository, providerRepository);
    }

    @Test
    void canGetOneAppointmentById() {

        LocalDate dt = LocalDate.parse("2018-11-01");
        //LocalTme tm = LocalTme.parse("2018-11-01");
        LocalTime tm = LocalTime.parse("12:00");
        // setup
        Provider provider1 = new Provider("MIU Clinic");
        Appointment appointment = new Appointment(dt,tm, "canada", provider1);
        when(appointmentRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.ofNullable(appointment));
        appointmentService.findById(2L);

        // assertion
        verify(appointmentRepository).findById(2L);
    }

    @Test
    void canAddAppointment() throws ParseException {
      Provider provider1= new Provider("MIU Clinic");

        LocalDate dt = LocalDate.parse("2018-11-01");
        //LocalTme tm = LocalTme.parse("2018-11-01");
        LocalTime tm = LocalTime.parse("12:00");





        Appointment appointment2 = new Appointment(dt,tm, "canada", provider1);
    when(providerRepository.findById(anyLong()))
           .thenReturn(java.util.Optional.ofNullable(provider1));
   appointmentService.save(appointment2);

    // assertion
    assertEquals(provider1, appointment2.getProvider());
      // verify(appointmentRepository).save(appointment2);










}

@Test
    void canGetAllAppointments(){

 List<Appointment> appointments = new ArrayList<>();


    LocalDate dt = LocalDate.parse("2018-11-01");
    //LocalTme tm = LocalTme.parse("2018-11-01");
    LocalTime tm = LocalTime.parse("12:00");
    // setup
    Provider provider1 = new Provider("MIU Clinic");
    Provider provider2 = new Provider("MIU TM office");
    Appointment appointment1 = new Appointment(dt,tm, "canada1", provider1);
    Appointment appointment2 = new Appointment(dt,tm, "canada2", provider2);

    appointments.add(appointment1);
    appointments.add(appointment2);






//    Status s1 = Status.ACCEPTED;
//    Status s2 = Status.ACCEPTED;
//    ReservationType restype1 = ReservationType.EXPEDIT;
//    ReservationType restype2 = ReservationType.EXPEDIT;
//    LocalDate dt = LocalDate.parse("2018-11-01");
//
//    LocalTime tm = LocalTime.parse("12:00");
//    Provider provider1 = new Provider("MIU Clinic");
//    Provider provider2 = new Provider("MIU TM office");
//    Client client1 = new Client();
//
//    Reservation res1 = new Reservation(s1,dt,tm,restype1, "to see u",provider1, client1, null);
//    Reservation res2 = new Reservation(s2,dt,tm,restype2, "to see u",provider2, client1, null);





}

}
