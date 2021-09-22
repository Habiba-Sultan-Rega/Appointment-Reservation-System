package com.ea.group6.EmailMicroService.EmailRepository;

import com.ea.group6.EmailMicroService.Domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
@Transactional
public interface EmailRepository extends JpaRepository<Email, Integer> {
}
