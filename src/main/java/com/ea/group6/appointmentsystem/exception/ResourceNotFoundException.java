package com.ea.group6.appointmentsystem.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String entityName, String message) {
        super(entityName +" "+ message);
    }
}