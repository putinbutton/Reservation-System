package com.example.Reservation.System;

import com.example.Reservation.System.Barber.Barber;
import com.example.Reservation.System.Barber.BarberRepository;
import com.example.Reservation.System.Client.Client;
import com.example.Reservation.System.Client.ClientRepository;
import com.example.Reservation.System.HairService.HairService;
import com.example.Reservation.System.HairService.HairServiceRepository;
import com.example.Reservation.System.Reservation.CreateReservationRequest;
import com.example.Reservation.System.Reservation.ReservationRepository;
import com.example.Reservation.System.Reservation.ReservationService;
import com.example.Reservation.System.common.ReservationsConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BarberRepository barberRepository;

    @Mock
    private HairServiceRepository hairServiceRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void shouldThrowExceptionWhenBarberAlreadyBooked() {
        LocalDateTime reservationDateTime = LocalDateTime.of(2026, 8, 1, 14, 30);

        CreateReservationRequest request = new CreateReservationRequest(
                1L,
                1L,
                1L,
                reservationDateTime
        );

        when(clientRepository.findById(1L)).thenReturn(Optional.of(new Client()));
        when(barberRepository.findById(1L)).thenReturn(Optional.of(new Barber()));
        when(hairServiceRepository.findById(1L)).thenReturn(Optional.of(new HairService()));

        when(reservationRepository.existsByBarberIdAndReservationDateTime(1L, reservationDateTime)).thenReturn(true);

        assertThrows(
                ReservationsConflictException.class,
                () -> reservationService.createReservation(request)
        );
    }
}
