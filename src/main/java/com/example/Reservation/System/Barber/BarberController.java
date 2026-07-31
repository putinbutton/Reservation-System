package com.example.Reservation.System.Barber;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/barbers")
public class BarberController {
    private final BarberService barberService;

    @PostMapping
    public Barber createBarber(@Valid @RequestBody Barber barber) {
        return barberService.createBarber(barber);
    }

    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    @GetMapping("/{id}")
    public Barber getBarberById(@PathVariable Long id) {
        return barberService.getBarberById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBarber(@PathVariable Long id){
        barberService.deleteBarber(id);
    }
}
