package edu.mum.apointementsystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No reservation is found")
public class NoReservationException extends RuntimeException{
}
