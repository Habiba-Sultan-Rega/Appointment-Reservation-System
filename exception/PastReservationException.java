package edu.mum.apointementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE , reason = "reservation expired!")
public class PastReservationException extends RuntimeException {
}
