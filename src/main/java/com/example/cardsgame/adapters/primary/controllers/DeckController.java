package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.usecases.CreateDeck;
import com.example.cardsgame.businesslogic.usecases.RemoveDeck;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/deck")
public class DeckController {

    private final CreateDeck createDeck;
    private final RemoveDeck removeDeck;

    public DeckController(CreateDeck createDeck, RemoveDeck removeDeck) {
        this.createDeck = createDeck;
        this.removeDeck = removeDeck;
    }

    @PostMapping("/add-deck")
    @ResponseStatus(CREATED)
    public void createDeck() {
        createDeck.handle(UUID.randomUUID());
    }

    @DeleteMapping("remove-deck/{deckId}")
    @ResponseStatus(NO_CONTENT)
    public String removeDeck(@PathVariable UUID deckId) {
        return removeDeck.handle(deckId);
    }

    @GetMapping
    public Collection<Deck> findAllDecks() {
        return createDeck.findAllDecks().values();
    }
}
