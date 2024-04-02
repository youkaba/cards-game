package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.UUID;

public class CreateGame {

    private final GameRepository gameRepository;

    public CreateGame(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void handle(UUID gameId) {
       gameRepository.save(new Game(gameId));
    }
}



