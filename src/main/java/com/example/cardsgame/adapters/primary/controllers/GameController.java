package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Game;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/game")
public record GameController(CreateGame createGame) {

    @PostMapping("/add-game")
    @ResponseStatus(CREATED)
    public void createGame(@RequestBody GameRequestBody gameRequestBody) {
        createGame.handle(UUID.randomUUID(), gameRequestBody.name());
    }

    @GetMapping()
    public Collection<Game> findAllGames() {
        return createGame.findAllGame();
    }

    public record GameRequestBody(String name) { }
}
