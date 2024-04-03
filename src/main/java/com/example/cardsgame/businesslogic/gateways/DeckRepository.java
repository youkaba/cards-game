package com.example.cardsgame.businesslogic.gateways;

import com.example.cardsgame.businesslogic.models.Deck;

import java.util.Map;

public interface DeckRepository {
    void createDeck(Deck deck);

    Map<String, Deck> allDecks();
}
