package com.example.cardsgame.businesslogic.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class Player {
    private final UUID id;
    private final List<Card> cards = new ArrayList<>();
    private Integer totalValue = 0;

    public Player(UUID id) {
        this.id = id;
    }

    public void addCard(Card card) {
        cards.add(card);
        totalValue += card.getValue();
    }
}
