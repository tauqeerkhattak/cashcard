package com.example.cashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (data.isPresent()) {
            return ResponseEntity.ok(data.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
//        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
