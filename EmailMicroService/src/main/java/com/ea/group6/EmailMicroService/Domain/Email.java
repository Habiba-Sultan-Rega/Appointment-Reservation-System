package com.ea.group6.EmailMicroService.Domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="Notification")
public class Email {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="sender")
    private String from;
    @Column(name="recepient")
    private String to;
    @Column(name="Ssubject")
    private String subject;
    @Column(name="Body")
    private String text;
    private LocalDateTime datetime;

    public Email(String to, String subject, String from, String text, LocalDateTime datetime) {
        this.to = to;
        this.subject = subject;
        this.from = from;
        this.text = text;
        this.datetime= datetime;
    }
}
