package com.example.Reservation.System.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<com.example.Reservation.System.Reservation.Reservation, Long> {

    boolean existsByBarberIdAndReservationDateTime(Long barberId, LocalDateTime reservationDateTime);
}
