package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Deck;

import java.util.Optional;
import java.util.UUID;

public class RemoveDeck {
    private final GameRepository gameRepository;
    private final DeckRepository deckRepository;

    public RemoveDeck(GameRepository gameRepository, DeckRepository deckRepository) {
        this.gameRepository = gameRepository;
        this.deckRepository = deckRepository;
    }

    public String handle(UUID deckId) {
        Optional<Deck> deck = gameRepository.findDeckId(deckId);
        if (deck.isPresent()) {
            return "Deck %s already exist in game ".formatted(deck.get().getId());
        }
        deckRepository.removeDeck(deckRepository.byId(deckId));

        return "Deck %s has been removed".formatted(deckId);
    }
}
