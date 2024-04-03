package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.usecases.CreateDeck;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/deck")
public class DeckController {

    private final CreateDeck createDeck;

    public DeckController(CreateDeck createDeck) {
        this.createDeck = createDeck;
    }

    @PostMapping("/add-deck")
    @ResponseStatus(CREATED)
    public void createDeck() {
        createDeck.handle(UUID.randomUUID());
    }

    @GetMapping
    public Collection<Deck> findAllDecks() {
        return createDeck.findAllDecks().values();
    }
}
