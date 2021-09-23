package com.ea.group6.appointmentsystem.service.provider;

import com.ea.group6.appointmentsystem.domain.Provider;
import com.ea.group6.appointmentsystem.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProviderService {
    List<Provider> findAll();

    Optional<Provider> findById(Long id);

    void delete(Provider provider);

    Provider update(Provider provider);

    void save(Provider provider);
}
