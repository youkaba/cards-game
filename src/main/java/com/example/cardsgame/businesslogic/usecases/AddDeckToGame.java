package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.UUID;

public class AddDeckToGame {

    private final GameRepository gameRepository;
    private final DeckRepository deckRepository;

    public AddDeckToGame(GameRepository gameRepository, DeckRepository deckRepository) {
        this.gameRepository = gameRepository;
        this.deckRepository = deckRepository;
    }

    public Game handle(UUID gameId, UUID deckId) {
        var game = gameRepository.byId(gameId);
        var deck = deckRepository.byId(deckId);
        game.addDeck(deck);
        return game;

    }
}
