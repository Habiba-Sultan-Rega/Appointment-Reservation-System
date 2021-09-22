package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from Appointment where category_id = :category_id", nativeQuery = true)
    public List<Appointment> findAllAppointmentsGivenCategoryId(@Param("category_id") Long id);
}
