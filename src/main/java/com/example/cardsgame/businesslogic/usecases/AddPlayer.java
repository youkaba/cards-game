package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.exceptions.AlreadyExistException;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.models.Player;

import java.util.Optional;
import java.util.UUID;

public class AddPlayer {

    private final GameRepository gameRepository;

    public AddPlayer(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Player handle(UUID gameId, UUID playerId) {
        Game game = gameRepository.byId(gameId);
        findOrThrow(game, playerId);
        return game.addPlayer(playerId);

    }

    private static void findOrThrow(Game game, UUID playerId) {
        Optional<Player> player = game.findPlayer(playerId);
        if (player.isPresent()) {
            throw new AlreadyExistException("Player %s already exist".formatted(player));
        }
    }
}
