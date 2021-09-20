package com.ea.group6.appointmentsystem.service.category;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long categoryId);

    List<Appointment> findAllAppointmentsGivenCategoryId(Long categoryId);

    Appointment findSingleAppointmentGivenCategoryId(Long categoryId, Long appointmentId);

    void delete(Category category);

    Category update(Category category);

    void save(Category category);
}
