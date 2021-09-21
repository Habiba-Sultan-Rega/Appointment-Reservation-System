package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Value("30 mins")
    private String duration;

    @OneToMany(mappedBy = "category")
    private List<Appointment> appointmentList;

    public Category(String title, String duration, List<Appointment> appointmentList) {
        this.title = title;
        this.duration = duration;
        this.appointmentList = appointmentList;
    }
}
