package com.example.cardsgame.unit.com.exemple.businesslogic.usecases;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryDeckRepository;
import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryGameRepository;
import com.example.cardsgame.businesslogic.exceptions.NotFoundException;
import com.example.cardsgame.businesslogic.models.Deck;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.models.Player;
import com.example.cardsgame.businesslogic.usecases.*;
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
    private static final UUID playerId = fromString("c56e3f3e-3d3d-3e3a-3e3e-3e3e3e3e3e3f");
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
    @DisplayName("cannot remove deck after add to a game")
    void cannotRemoveDeckAfterAddToAGame() {
        createAGame(gameId);
        deckRepository.createDeck(new Deck(deckId));
        addDeckToGameDeck();
        assertRemoveDeck(removeDeck(deckId));

    }

    @Test
    @DisplayName("can remove a deck")
    void canRemoveADeck() {
        deckRepository.createDeck(new Deck(deckId));
        assertThat(removeDeck(deckId)).isEqualTo("Deck %s has been removed".formatted(deckId));

    }

    @Test
    @DisplayName("add a player from game")
    void addAPlayerFromGame() {
        createAGame(gameId);
        addPlayerToGame(gameId, playerId);
        assertAddPlayer(Map.of(playerId, new Player(playerId)));
    }

    @Test
    @DisplayName("removePlayer from game")
    void removePlayerFromGame() {

        createAGame(gameId);
        addPlayerToGame(gameId, playerId);
        removePlayer(gameId, playerId);
        assertRemovePlayer(new Player(playerId));
    }


    @Test
    @DisplayName("add a deck to a game deck")
    void addADeckToAGameDeck() {
        createAGame(gameId);
        deckRepository.createDeck(new Deck(deckId));
        addDeckToGameDeck();
        assertAddDeck(Map.of(deckId, new Deck(deckId)));
    }

    @Test
    @DisplayName("deal cards to player")
    void dealCardsToPlayer() {
        createAGame(gameId);
        deckRepository.createDeck(new Deck((deckId)));
        addDeckToGameDeck();
        addPlayerToGame(gameId, playerId);
        dealCardToPlayer(gameId, playerId);
        dealCardToPlayer(gameId, playerId);
    }

    private void dealCardToPlayer(UUID gameId, UUID playerId) {
        new DealCard(gameRepository).handle(gameId, playerId);
    }

    private void removePlayer(UUID gameId, UUID playerId) {
        new RemovePlayer(gameRepository).handle(gameId, playerId);
    }

    private void addPlayerToGame(UUID gameId, UUID playerId) {
        new AddPlayer(gameRepository).handle(gameId, playerId);
    }

    private String removeDeck(UUID deckId) {
        return new RemoveDeck(gameRepository, deckRepository).handle(deckId);
    }

    private void addDeckToGameDeck() {
        new AddDeckToGame(gameRepository, deckRepository).handle(gameId, deckId);
    }

    private void deleteAGame(UUID gameId) {
        new DeleteGame(gameRepository).handle(gameId);
    }

    private void createAGame(UUID gameId) {
        new CreateGame(gameRepository).handle(gameId, gameName);
    }

    private void assertAddPlayer(Map<UUID, Player> player) {
        var actual = gameRepository.byId(gameId).getPlayers();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(player);
    }

    private void assertRemovePlayer(Player player) {
        var actual = gameRepository.byId(gameId).getPlayers();
        assertThat(actual).doesNotContainValue(player);
    }

    private void assertRemoveDeck(String message) {
        assertThat(message).isEqualTo("Deck %s already exist in game ".formatted(deckId));
    }

    private void assertAddDeck(Map<UUID, Deck> decks) {
        Game game = gameRepository.byId(gameId);
        assertThat(game.getDecks()).isNotEmpty()
                .containsAllEntriesOf(decks);
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
}
