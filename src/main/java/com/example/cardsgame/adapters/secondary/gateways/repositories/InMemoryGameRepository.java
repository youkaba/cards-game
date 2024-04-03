package com.example.cardsgame.adapters.secondary.gateways.repositories;

import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {

    private final List<Game> games = new ArrayList<>();

    @Override
    public void save(Game game) {
        games.add(game);
    }

    @Override
    public List<Game> allGames() {
        return Collections.unmodifiableList(games);
    }
}
