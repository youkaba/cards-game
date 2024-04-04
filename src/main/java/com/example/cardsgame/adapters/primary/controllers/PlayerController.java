package com.example.cardsgame.adapters.primary.controllers;

import com.example.cardsgame.businesslogic.models.Player;
import com.example.cardsgame.businesslogic.usecases.GetListPlayersSorted;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final GetListPlayersSorted getListPlayersSorted;

    public PlayerController(GetListPlayersSorted getListPlayersSorted) {
        this.getListPlayersSorted = getListPlayersSorted;
    }

    @GetMapping("/{gameId}/sort-player-by-value")
    public Collection<Player> getPlayersSortedByTotalValue(@PathVariable UUID gameId) {
        return getListPlayersSorted.handle(gameId);
    }
}
