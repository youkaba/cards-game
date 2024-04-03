package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Card;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.models.Player;

import java.util.Collection;
import java.util.UUID;

public class NumberPlayerCards {
    private final GameRepository gameRepository;

    public NumberPlayerCards(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Collection<Card> handle(UUID gameId, UUID playerId) {
        Game game = gameRepository.byId(gameId);
        Player player = findOrThrowPlayer(playerId, game);
        return player.getCards();
    }

    private static Player findOrThrowPlayer(UUID playerId, Game game) {
        return game.findPlayer(playerId).orElseThrow(() -> new NotFoundException("Player %s does not exist".formatted(playerId)));
    }
}
