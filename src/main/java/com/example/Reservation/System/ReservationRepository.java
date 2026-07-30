package com.example.Reservation.System;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByBarberIdAndReservationDateTime(Long barberId, LocalDateTime reservationDateTime);
}
