package com.example.Reservation.System.Client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById (@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient (@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
