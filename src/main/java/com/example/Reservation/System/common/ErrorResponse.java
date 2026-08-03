package com.example.Reservation.System.common;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors
) {
}
