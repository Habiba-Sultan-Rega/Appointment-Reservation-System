package com.ea.group6.appointmentsystem.controller;

import com.ea.group6.appointmentsystem.domain.Provider;

import com.ea.group6.appointmentsystem.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<Provider> findAll(){
        return providerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Provider> findById(@PathVariable Long id){
        return providerService.findById(id);
    }

}
