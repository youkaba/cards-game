package com.example.cardsgame.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


@Getter
@EqualsAndHashCode
@ToString
public class Deck {

    private UUID id;
    @JsonIgnore
    private CopyOnWriteArrayList<Card> cards = new CopyOnWriteArrayList<>();
    public static final int DECK_SIZE = 52;

    public Deck(UUID id) {
        this.id = id;
        init();
        System.out.println();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            for (int value = Card.ACE; value <= Card.KING; value++) {
                cards.add(new Card(suit, value, this.id));
            }
        }
    }

    public void returnCard(Card card) {
        // TODO
    }
}
