package com.example.cardsgame.adapters.primary.config;

import com.example.cardsgame.businesslogic.gateways.DeckRepository;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import com.example.cardsgame.businesslogic.usecases.AddDeckToGame;
import com.example.cardsgame.businesslogic.usecases.CreateDeck;
import com.example.cardsgame.businesslogic.usecases.CreateGame;
import com.example.cardsgame.businesslogic.usecases.DeleteGame;
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
}
