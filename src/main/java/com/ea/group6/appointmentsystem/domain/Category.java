package com.ea.group6.appointmentsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=2, max=10, message="title should have minimum of 2 characters")
    private String title;

    @NotNull
    @Value("30 mins")
    private String duration;

    public Category(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }
}
