package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.models.Deck;

import java.util.Map;
import java.util.UUID;

public class CreateDeck {

    private final DeckRepository deckRepository;

    public CreateDeck(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public void handle(UUID deckId) {
        deckRepository.createDeck(new Deck(deckId));
    }

    public Map<String, Deck> findAllDecks() {
       return deckRepository.allDecks();
    }
}
