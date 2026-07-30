package com.example.Reservation.System;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        Long clientId,
        Long barberId,
        Long hairServiceId,
        LocalDateTime reservationDateTime
) {
}
