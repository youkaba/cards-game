package com.example.cardsgame.businesslogic.gateways;

import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.models.Game;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {

    void save(Game game);

    Collection<Game> allGames();

    Game byId(UUID gameId);

    void remove(Game game);

    Optional<Deck> findDeckId(UUID deckId);
}
