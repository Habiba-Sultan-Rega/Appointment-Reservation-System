package com.ea.group6.appointmentsystem.service.provider;

import com.ea.group6.appointmentsystem.domain.Provider;

import com.ea.group6.appointmentsystem.repository.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService{
    ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository){

        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public void delete(Provider provider) {
        providerRepository.delete(provider);
    }

    @Override
    public Provider update(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void save(Provider provider) {
        providerRepository.save(provider);
    }


}
