package com.example.Reservation.System.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationsConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleReservationConflict(ReservationsConflictException exception) {
        return new ErrorResponse(
                "RESERVATION_CONFLICT",
                exception.getMessage(),
                LocalDateTime.now(),
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                return new ErrorResponse(
                        "VALIDATION_ERROR",
                        "Invalid request data",
                        LocalDateTime.now(),
                        errors
                );
    }



}
