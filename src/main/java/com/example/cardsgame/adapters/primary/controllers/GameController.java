package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Card;
import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.models.Player;
import com.example.cardsgame.businesslogic.usecases.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/game")
public record GameController(CreateGame createGame, DeleteGame deleteGame,
                             AddDeckToGame addDeckToGame, AddPlayer addPlayer,
                             RemovePlayer removePlayer, DealCard dealCard,
                             NumberPlayerCards numberPlayerCards) {

    @PostMapping("/add-game")
    @ResponseStatus(CREATED)
    public void createGame(@RequestBody GameRequestBody gameRequestBody) {
        createGame.handle(UUID.randomUUID(), gameRequestBody.name());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteGame(@PathVariable UUID id) {
        deleteGame.handle(id);
    }

    @PutMapping("/{gameId}/decks/{deckId}")
    public Game addDeck(@PathVariable UUID gameId, @PathVariable UUID deckId) {
        return addDeckToGame.handle(gameId, deckId);
    }

    @PostMapping("/add-player/{gameId}")
    public Player addPlayer(@PathVariable UUID gameId) {
        return addPlayer.handle(gameId, UUID.randomUUID());
    }

    @DeleteMapping("/remove-player/{gameId}/{playerId}")
    @ResponseStatus(NO_CONTENT)
    public void deletePlayer(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        removePlayer.handle(gameId, playerId);
    }

    @GetMapping("/players/deal-card/{gameId}/{playerId}")
    public Card dealCard(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        return dealCard.handle(gameId, playerId);
    }

    @GetMapping("/players/get-cards/{gameId}/{playerId}")
    public Collection<Card> getPlayersCards(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        return numberPlayerCards.handle(gameId, playerId);
    }


    @GetMapping()
    public Collection<Game> findAllGames() {
        return createGame.findAllGame();
    }

    public record GameRequestBody(String name) {
    }
}
