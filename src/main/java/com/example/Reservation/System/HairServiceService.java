package com.example.Reservation.System;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HairServiceService {

    private final HairServiceRepository hairServiceRepository;

    public HairService createHairService(HairService hairService) {
        return hairServiceRepository.save(hairService);
    }

    public List<HairService> getAllHairServices() {
        return hairServiceRepository.findAll();
    }

    public HairService getHairServiceById (Long id) {
        return hairServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hair service not found"));
    }

    public void deleteHairService(Long id) {
        hairServiceRepository.deleteById(id);
    }
}
