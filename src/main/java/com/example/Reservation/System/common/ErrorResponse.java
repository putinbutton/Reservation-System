package com.example.Reservation.System.common;

import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {
}
