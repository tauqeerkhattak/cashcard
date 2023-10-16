package com.example.cashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class CashCardController {
    private CashCardRepository repository;

    CashCardController(CashCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cashcard/{requestId}")
    public ResponseEntity<CashCard> getCashCard(@PathVariable Long requestId) {
        System.out.println("REQUESTED ID: " + requestId);
        Optional<CashCard> data = repository.findById(requestId);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cashcard/")
    public ResponseEntity<Void> createCashCard(@RequestBody CashCard newCard, UriComponentsBuilder ucb) {
        CashCard savedCard = repository.save(newCard);
        URI location = ucb.path("api/cashcard/{id}").buildAndExpand(savedCard.id()).toUri();
        return ResponseEntity.created(location).build();
    }
}
