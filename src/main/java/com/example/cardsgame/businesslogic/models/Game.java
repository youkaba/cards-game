package com.example.cardsgame.businesslogic.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Game {
    private final UUID id;
    private final String gameName;
    private Map<UUID, Deck> decks = new HashMap<>();

    public void addDeck(Deck deck) {
        decks.put(deck.getId(), deck);
    }
}
