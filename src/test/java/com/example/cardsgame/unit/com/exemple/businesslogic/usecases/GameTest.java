package com.example.cardsgame.unit.com.exemple.businesslogic.usecases;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryGameRepository;
import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import com.example.cardsgame.businesslogic.usecases.DeleteGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private static final UUID gameId = fromString("c56e3f3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e");
    private static final UUID gameId2 = fromString("c56e3f3e-3e3e-3e3e-3e3e-3e3e3e3e3e3f");
    private static final String gameName = "poker";

    private final InMemoryGameRepository gameRepository = new InMemoryGameRepository();


    @Test
    @DisplayName("can create a game")
    void can_create_a_game() {
        createAGame(gameId);
        assertCreatedGame(
                new Game(gameId, gameName)
        );
    }

    @Test
    @DisplayName("cannot delete a game")
    void cannotDeleteAGame() {

        Assertions.assertThatThrownBy(() -> deleteAGame(gameId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Game %s does not exist".formatted(gameId));
    }

    @Test
    @DisplayName("can delete a game")
    void canDeleteAGame() {

        createAGame(gameId);
        createAGame(gameId2);
        deleteAGame(gameId2);
        assertDeleteGame();
    }

    private void deleteAGame(UUID gameId) {
        new DeleteGame(gameRepository).handle(gameId);
    }

    private void assertDeleteGame() {
        assertThat(gameRepository.allGames()).doesNotContain(
                new Game(gameId2, gameName)
        );

    }

    private void assertCreatedGame(Game... game) {
        List<Game> actual = gameRepository.allGames();
        assertThat(actual).containsExactly(game);
    }

    private void createAGame(UUID gameId) {
        new CreateGame(gameRepository).handle(gameId, gameName);
    }
}
