package com.example.cardsgame.adapters.secondary.gateways.repositories;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.models.Deck;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDeckRepository implements DeckRepository {

    private Map<String, Deck> decks = new HashMap<>();

    @Override
    public void createDeck(Deck deck) {
        decks.put(deck.getId().toString(), deck);
    }

    @Override
    public Map<String, Deck> allDecks() {
        return Collections.unmodifiableMap(decks);
    }
}
