package com.example.Reservation.System.Reservation;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@Valid @RequestBody CreateReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReservations (@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
