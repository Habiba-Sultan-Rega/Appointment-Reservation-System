package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="Notification")
public class EmailNotification {
    @Id
    @GeneratedValue
    private Long id;

    private String from_;
    private String to_;
    private String subject;
    private String content;
    private LocalDate date;
    private LocalTime time;

    public EmailNotification(String from_, String to_, String subject, String content, LocalDate date, LocalTime time) {
        this.from_ = from_;
        this.to_ = to_;
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.time = time;
    }
}
