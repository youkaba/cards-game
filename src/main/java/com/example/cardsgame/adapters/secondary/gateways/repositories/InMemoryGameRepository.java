package com.example.cardsgame.adapters.secondary.gateways.repositories;

import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.*;

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

    @Override
    public Game byId(UUID gameId) {
        return games.stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Game %s does not exist".formatted(gameId.toString())));
    }

    @Override
    public void remove(Game game) {
        games.remove(game);
    }

}
