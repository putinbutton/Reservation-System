package com.example.Reservation.System.Reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        @NotNull
        Long clientId,

        @NotNull
        Long barberId,

        @NotNull
        Long hairServiceId,

        @NotNull
        @Future
        LocalDateTime reservationDateTime
) {
}
