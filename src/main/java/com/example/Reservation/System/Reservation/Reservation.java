package com.example.Reservation.System.Reservation;

import com.example.Reservation.System.Barber.Barber;
import com.example.Reservation.System.Client.Client;
import com.example.Reservation.System.HairService.HairService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reservationDateTime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Barber barber;

    @ManyToOne
    private HairService hairService;

}
