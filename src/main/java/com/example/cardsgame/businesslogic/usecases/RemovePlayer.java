package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.models.Player;

import java.util.UUID;

public class RemovePlayer {

    private final GameRepository gameRepository;

    public RemovePlayer(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void handle(UUID gameId, UUID playerId) {
        Game game = gameRepository.byId(gameId);
        Player player = findOrThrowPlayer(playerId, game);
        game.removePlayer(player);

    }

    private static Player findOrThrowPlayer(UUID playerId, Game game) {
        return game.findPlayer(playerId).orElseThrow(() -> new NotFoundException("Player %s does not exist".formatted(playerId)));
    }
}
