package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.GameRepository;

import java.util.UUID;

public class DeleteGame {

    private final GameRepository gameRepository;

    public DeleteGame(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void handle(UUID gameId) {
        var game = gameRepository.byId(gameId);
        gameRepository.remove(game);
    }
}
