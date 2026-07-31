package com.example.Reservation.System.common;

public class ReservationsConflictException extends RuntimeException{

    public ReservationsConflictException(String message) {
        super(message);
    }
}
