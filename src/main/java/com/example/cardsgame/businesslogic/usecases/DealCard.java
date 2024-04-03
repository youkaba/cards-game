package com.example.cardsgame.businesslogic.usecases;

import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Card;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.Objects;
import java.util.UUID;

public class DealCard {
    private final GameRepository gameRepository;

    public DealCard(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    public Card handle(UUID gameId, UUID playerId) {
        Game game = gameRepository.byId(gameId);
        Card card = game.dealCard(playerId);
        if (Objects.isNull(card)) {
            throw new NotFoundException("Card not found");
        }
        return card;
    }
}
