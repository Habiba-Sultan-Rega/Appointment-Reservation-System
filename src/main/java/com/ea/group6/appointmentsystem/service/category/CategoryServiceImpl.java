package com.ea.group6.appointmentsystem.service.category;

import com.ea.group6.appointmentsystem.domain.Appointment;
import com.ea.group6.appointmentsystem.domain.Category;
import com.ea.group6.appointmentsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Appointment> findAllAppointmentsGivenCategoryId(Long categoryId) {
        return findById(categoryId).get().getAppointmentList();
    }

    @Override
    public Appointment findSingleAppointmentGivenCategoryId(Long categoryId, Long appointmentId) {
        return findById(categoryId).get().getAppointmentList()
                .stream()
                .filter(appointment -> appointment.getId() == appointmentId)
                .findFirst()
                .get();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
