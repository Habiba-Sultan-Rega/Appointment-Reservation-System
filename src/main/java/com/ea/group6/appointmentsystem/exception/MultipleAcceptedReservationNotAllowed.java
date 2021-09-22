package com.ea.group6.appointmentsystem.exception;

public class MultipleAcceptedReservationNotAllowed extends RuntimeException {
    public MultipleAcceptedReservationNotAllowed() {
    }

    public MultipleAcceptedReservationNotAllowed(String message) {
        super(message);
    }
}