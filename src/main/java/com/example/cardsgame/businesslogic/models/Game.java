package com.example.cardsgame.businesslogic.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Game {
    private final UUID id;
    private final String gameName;
    private final Map<UUID, Deck> decks = new HashMap<>();
    private final Map<UUID, Player> players = new HashMap<>();

    public void addDeck(Deck deck) {
        decks.put(deck.getId(), deck);
    }

    public Player addPlayer(UUID id) {
        var player = new Player(id);
        players.put(player.getId(), player);
        return player;
    }

    public Optional<Player> findPlayer(UUID player) {
        return Optional.ofNullable(players.get(player));
    }

    public void removePlayer(Player player) {
        // when we remove player, need to return back card to the game deck
        player.getCards().forEach(card -> {
            var cardDeck = decks.get(card.getDeckId());
            cardDeck.returnCard(card);
        });
        players.remove(player.getId());

    }
}
