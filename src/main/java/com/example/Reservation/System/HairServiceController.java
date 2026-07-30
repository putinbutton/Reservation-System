package com.example.Reservation.System;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hair-services")
@RequiredArgsConstructor
public class HairServiceController {

    private final HairServiceService hairServiceService;

    @PostMapping
    public HairService createHairService(@RequestBody HairService hairService) {
        return hairServiceService.createHairService(hairService);
    }

    @GetMapping
    public List<HairService> getAllHairServices () {
        return hairServiceService.getAllHairServices();
    }

    @GetMapping("/{id}")
    public HairService getHairServiceById(@PathVariable Long id) {
        return hairServiceService.getHairServiceById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHairService(@PathVariable Long id){
        hairServiceService.deleteHairService(id);
    }

}

