package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.usecases.AddDeckToGame;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import com.example.cardsgame.businesslogic.usecases.DeleteGame;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/game")
public record GameController(CreateGame createGame, DeleteGame deleteGame,
                             AddDeckToGame addDeckToGame) {

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
    @GetMapping()
    public Collection<Game> findAllGames() {

        return createGame.findAllGame();
    }

    public record GameRequestBody(String name) { }
}
