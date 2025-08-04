package com.upwork.movercrm.controller;

import com.upwork.movercrm.entity.Client;
import com.upwork.movercrm.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.get();
            existingClient.setName(clientDetails.getName());
            existingClient.setPhone(clientDetails.getPhone());
            existingClient.setEmail(clientDetails.getEmail());
            existingClient.setAddress(clientDetails.getAddress());
            existingClient.setNotes(clientDetails.getNotes());
            existingClient.setFavorite(clientDetails.isFavorite());
            
            Client updatedClient = clientRepository.save(existingClient);
            return ResponseEntity.ok(updatedClient);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam String searchTerm) {
        List<Client> clients = clientRepository.searchClients(searchTerm);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/favorites")
    public ResponseEntity<List<Client>> getFavoriteClients() {
        List<Client> favoriteClients = clientRepository.findByIsFavoriteTrue();
        return ResponseEntity.ok(favoriteClients);
    }
    
    @PutMapping("/{id}/favorite")
    public ResponseEntity<Client> toggleFavorite(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.get();
            existingClient.setFavorite(!existingClient.isFavorite());
            Client updatedClient = clientRepository.save(existingClient);
            return ResponseEntity.ok(updatedClient);
        }
        return ResponseEntity.notFound().build();
    }
} 