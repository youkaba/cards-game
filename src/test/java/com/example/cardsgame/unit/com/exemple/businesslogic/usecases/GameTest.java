package com.example.cardsgame.unit.com.exemple.businesslogic.usecases;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryDeckRepository;
import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryGameRepository;
import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.usecases.AddDeckToGame;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import com.example.cardsgame.businesslogic.usecases.DeleteGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.fromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameTest {
    private static final UUID gameId = fromString("c56e3f3e-3e3e-3e3e-3e3e-3e3e3e3e3e3e");
    private static final UUID gameId2 = fromString("c56e3f3e-3e3e-3e3e-3e3e-3e3e3e3e3e3f");
    private static final UUID deckId = fromString("c56e3f3e-3d3d-3e3e-3e3e-3e3e3e3e3e3f");
    private static final String gameName = "poker";

    private final InMemoryGameRepository gameRepository = new InMemoryGameRepository();
    private final InMemoryDeckRepository deckRepository = new InMemoryDeckRepository();


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

        assertThatThrownBy(() -> deleteAGame(gameId))
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

    @Test
    @DisplayName("add a deck to a game deck")
    void addADeckToAGameDeck() {
        createAGame(gameId);
        deckRepository.createDeck(new Deck(deckId));
        addDeckToGameDeck();
        assertAddDeck(Map.of(deckId, new Deck(deckId)));
    }

    private void assertAddDeck(Map<UUID, Deck> decks) {
        Game game = gameRepository.byId(gameId);
        assertThat(game.getDecks()).isNotEmpty()
                .containsAllEntriesOf(decks);
    }

    private void addDeckToGameDeck() {
        new AddDeckToGame(gameRepository, deckRepository).handle(gameId, deckId);
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
