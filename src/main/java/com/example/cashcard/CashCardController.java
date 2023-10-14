package com.example.cashcard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CashCardController {

    @GetMapping("/cashcard/{requestId}")
    public CashCard getCashCard(@PathVariable Long requestId) {
        return new CashCard(requestId,123.45);
    }
}
