package com.example.cardsgame.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@EqualsAndHashCode
@ToString
public class Deck {

    private final UUID id;
    @JsonIgnore
    private CopyOnWriteArrayList<Card> cards = new CopyOnWriteArrayList<>();
    @JsonIgnore
    private AtomicInteger dealtCards = new AtomicInteger(0);
    @JsonIgnore
    private Map<Suit, AtomicInteger> dealtCardsBySuit = new HashMap<>();
    public static final int DECK_SIZE = 52;

    public Deck(UUID id) {
        this.id = id;
        init();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            for (int value = Card.ACE; value <= Card.KING; value++) {
                cards.add(new Card(suit, value, this.id));
            }
            dealtCardsBySuit.put(suit, new AtomicInteger(0));
        }
    }

    public void returnCard(Card card) {
        // TODO
    }

    public Card dealCard(Player player) {
        Card card = null;
        if (dealtCards.get() < DECK_SIZE) {
            card = cards.get(dealtCards.getAndIncrement());
            if (card != null) {
                player.addCard(card);
                AtomicInteger cardsBySuit = dealtCardsBySuit.get(card.getSuit());
                cardsBySuit.getAndIncrement(); // Update cards by suit counters.
            }
        }
        return card;

    }

    public void shuffle() {
        Random random = new Random();
        int dealtCards = this.dealtCards.get();
        for (int i = dealtCards; i < Deck.DECK_SIZE; i++) {
            int randomNumber = dealtCards + random.nextInt((Deck.DECK_SIZE - dealtCards));
            Card tempCard = cards.get(i);
            cards.set(i, cards.get(randomNumber));
            cards.set(randomNumber, tempCard);
        }
    }

    public Map<Suit, Integer> getDealtCardsBySuit() {
        Map<Suit, Integer> dealtCardsCount = new HashMap<>();
        dealtCardsBySuit.forEach((key, value) -> dealtCardsCount.put(key, value.get()));
        return dealtCardsCount;
    }
}
