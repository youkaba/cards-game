package com.example.cardsgame.adapters.primary.config;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreateGame createGame(GameRepository gameRepository) {
        return new CreateGame(gameRepository);
    }
    @Bean
    public DeleteGame deleteGame(GameRepository gameRepository) {
        return new DeleteGame(gameRepository);
    }
    @Bean
    public CreateDeck createDeck(DeckRepository deckRepository) {
        return new CreateDeck(deckRepository);
    }
    @Bean
    public AddDeckToGame addDeckToGame(GameRepository gameRepository, DeckRepository deckRepository) {
        return new AddDeckToGame(gameRepository, deckRepository);
    }
    @Bean
    public RemoveDeck removeDeck(GameRepository gameRepository, DeckRepository deckRepository) {
        return new RemoveDeck(gameRepository, deckRepository);
    }

    @Bean
    public AddPlayer addPlayer(GameRepository gameRepository) {
        return new AddPlayer(gameRepository);
    }
    @Bean
    public RemovePlayer removePlayer(GameRepository gameRepository) {
        return new RemovePlayer(gameRepository);
    }
}
