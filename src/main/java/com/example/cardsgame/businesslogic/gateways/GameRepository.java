package com.example.cardsgame.businesslogic.gateways;

import com.example.cardsgame.businesslogic.models.Game;

import java.util.Collection;
import java.util.UUID;

public interface GameRepository {

    void save(Game game);

    Collection<Game> allGames();

    Game byid(UUID gameId);

    void remove(Game game);
}
