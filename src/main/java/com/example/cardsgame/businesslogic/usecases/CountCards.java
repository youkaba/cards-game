package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Suit;

import java.util.Map;
import java.util.UUID;

public class CountCards {

    private final GameRepository gameRepository;

    public CountCards(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Map<Suit, Integer> handle(UUID gameId) {
        var game = gameRepository.byId(gameId);
        return game.undealtCards();
    }
}
