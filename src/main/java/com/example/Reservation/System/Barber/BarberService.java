package com.example.Reservation.System.Barber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberService {

    private final BarberRepository barberRepository;

    public Barber createBarber(Barber barber) {
        return barberRepository.save(barber);
    }

    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public Barber getBarberById(Long id) {
        return barberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barber not found"));
    }

    public void deleteBarber(Long id){
        barberRepository.deleteById(id);
    }
}
