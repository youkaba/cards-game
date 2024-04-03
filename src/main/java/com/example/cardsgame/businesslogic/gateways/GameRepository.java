package com.example.cardsgame.businesslogic.gateways;

import com.example.cardsgame.businesslogic.models.Game;

import java.util.Collection;

public interface GameRepository {

    void save(Game game);

    Collection<Game> allGames();
}
