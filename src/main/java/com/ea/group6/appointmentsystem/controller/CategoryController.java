package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/{id}/appointments")
    public List<Appointment> findAllAppointmentsGivenCategoryId(@PathVariable Long categoryId){
        return categoryService.findAllAppointmentsGivenCategoryId(categoryId);
    }

    @GetMapping("/{id}/appointments/{id}")
    public Appointment findAllAppointmentsGivenCategoryId(@PathVariable Long categoryId, @PathVariable Long appointmentId){
        return categoryService.findSingleAppointmentGivenCategoryId(categoryId, appointmentId);
    }

    @PostMapping
    public void save(@Valid @RequestBody Category category){

        categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable(name = "id") Long categoryId, @Valid @RequestBody Category category) {
        categoryService.findById(categoryId).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long categoryId, @RequestBody Category category) {
        categoryService.findById(categoryId).orElseThrow(RuntimeException::new); //It should throw a custom exception; we need to write custom exception
        categoryService.delete(category);
    }
}
