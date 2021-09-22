package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.*;
import com.ea.group6.appointmentsystem.dto.AppointmentDTO;
import com.ea.group6.appointmentsystem.repository.ProviderRepository;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentService;
import com.ea.group6.appointmentsystem.service.appointment.AppointmentServiceImpl;
import com.ea.group6.appointmentsystem.service.provider.ProviderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentServiceImpl appointmentService;


    @Test
    void getAllAppointments(){

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

        when(appointmentService.findAll()).thenReturn(appoints);

        List<Appointment> actualAppointments = appointmentController.findAll();
        assertNotNull(actualAppointments );

        assertEquals(2, actualAppointments .size() );
    }

    //BELOW IS FAILING.
        @Test
        public void testGetAppointment() {

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


            when(appointmentService.findById(1L)).thenReturn(Optional.of(appointment1));

            Optional<Appointment> actualResponseAirport = appointmentController.findById(1L);

            assertNotNull(actualResponseAirport);

            assertEquals(appointment1, actualResponseAirport);


        }

        //NOT WORKING:
        @Test
    void addNewAppointment(){
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
            AppointmentDTO appointment1 = new AppointmentDTO(dt,tm, "canada", "30 mins", provider1, category2);
            Appointment appointment2 = new Appointment(dt,tm, "canada", "30 mins", provider1, category2, reserveList );


            appointmentController.save(appointment1);
            verify(appointmentService, times(1)).save(appointment2);

        }


        @Test
         void canDelete(){
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
            Long ids = appointment1.getId();

            Appointment expected = new Appointment();

            //appointmentController.delete(ids);
            Mockito.verify(appointmentService).delete(appointment1);

         }

}
