package com.ea.group6.appointmentsystem.repository;

import com.ea.group6.appointmentsystem.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
