package com.example.Reservation.System.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationsConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleReservationConflict(ReservationsConflictException exception) {
        return new ErrorResponse(
                "RESERVATION_CONFLICT",
                exception.getMessage(),
                LocalDateTime.now()
        );
    }



}
