package com.example.Reservation.System;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final BarberRepository barberRepository;
    private final HairServiceRepository hairServiceRepository;

    public Reservation createReservation(CreateReservationRequest request) {
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Barber barber = barberRepository.findById(request.barberId())
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        HairService hairService = hairServiceRepository.findById(request.hairServiceId())
                .orElseThrow(() -> new RuntimeException("Hair service not found"));

        boolean barberAlreadyBooked = reservationRepository
                .existsByBarberIdAndReservationDateTime(
                        request.barberId(),
                        request.reservationDateTime()
                );
        if(barberAlreadyBooked) {
            throw new RuntimeException("Barber already has a reservation at this time");
        }

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setBarber(barber);
        reservation.setHairService(hairService);
        reservation.setReservationDateTime(request.reservationDateTime());

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
