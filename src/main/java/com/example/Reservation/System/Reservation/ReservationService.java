package com.example.Reservation.System.Reservation;

import com.example.Reservation.System.Barber.Barber;
import com.example.Reservation.System.Barber.BarberRepository;
import com.example.Reservation.System.Client.Client;
import com.example.Reservation.System.Client.ClientRepository;
import com.example.Reservation.System.HairService.HairService;
import com.example.Reservation.System.HairService.HairServiceRepository;
import com.example.Reservation.System.common.ReservationsConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final com.example.Reservation.System.Reservation.ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final BarberRepository barberRepository;
    private final HairServiceRepository hairServiceRepository;

    public com.example.Reservation.System.Reservation.Reservation createReservation(com.example.Reservation.System.Reservation.CreateReservationRequest request) {
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
            throw new ReservationsConflictException("Barber already has a reservation at this time");
        }

        com.example.Reservation.System.Reservation.Reservation reservation = new com.example.Reservation.System.Reservation.Reservation();
        reservation.setClient(client);
        reservation.setBarber(barber);
        reservation.setHairService(hairService);
        reservation.setReservationDateTime(request.reservationDateTime());

        return reservationRepository.save(reservation);
    }

    public List<com.example.Reservation.System.Reservation.Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public com.example.Reservation.System.Reservation.Reservation getReservationById(Long id){
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
