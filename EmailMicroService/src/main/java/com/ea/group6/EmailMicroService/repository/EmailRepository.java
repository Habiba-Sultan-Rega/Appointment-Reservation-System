package com.ea.group6.EmailMicroService.repository;

import com.ea.group6.EmailMicroService.Domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmailRepository extends JpaRepository<Email, Long> {
}
