package com.example.cardsgame.unit.com.exemple.businesslogic.usecases;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryDeckRepository;
import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.usecases.CreateDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    private static final UUID deckId = fromString("c56e3f3e-3d3d-3e3e-3e3e-3e3e3e3e3e3f");

    private final InMemoryDeckRepository deckRepository = new InMemoryDeckRepository();
    @Test
    @DisplayName("can create a deck")
    void canCreateADeck() {
        createADeck(deckId);
        assertCreateDeck(new Deck(deckId));

    }

    private void assertCreateDeck(Deck deck) {
        var actual = deckRepository.allDecks();
        assertThat(actual).containsKey(deck.getId().toString());
    }

    private void createADeck(UUID deckId) {
        new CreateDeck(deckRepository).handle(deckId);
    }
}
