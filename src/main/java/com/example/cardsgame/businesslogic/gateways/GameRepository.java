package com.example.cardsgame.businesslogic.gateways;

import com.example.cardsgame.businesslogic.models.Game;

public interface GameRepository {

    void save(Game game);
}
