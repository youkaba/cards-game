package com.example.cardsgame.unit.com.exemple.businesslogic.usecases;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryGameRepository;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private static final UUID gameId = fromString("c56e3f3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e");

    private InMemoryGameRepository gameRepository = new InMemoryGameRepository();


    @Test
    void can_create_a_game() {
        createAGame(gameId);
        assertCreatedGame(
                new Game(gameId)
        );
    }

    private void assertCreatedGame(Game... game) {
        assertThat(gameRepository.allGames()).containsExactly(game);
    }

    private void createAGame(UUID gameId) {
        new CreateGame(gameRepository).handle(gameId);
    }
}
