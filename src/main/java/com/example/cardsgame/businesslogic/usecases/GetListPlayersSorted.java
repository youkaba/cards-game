package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Player;

import java.util.Collection;
import java.util.UUID;

public class GetListPlayersSorted {
    private final GameRepository gameRepository;

    public GetListPlayersSorted(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Collection<Player> handle(UUID gameId) {
        var game = gameRepository.byId(gameId);
        return game.findAllPlayersSortedByTotalValue();
    }
}
