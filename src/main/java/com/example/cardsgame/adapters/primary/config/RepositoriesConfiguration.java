package com.example.cardsgame.adapters.primary.config;

import com.example.cardsgame.adapters.secondary.gateways.repositories.InMemoryGameRepository;
import com.example.cardsgame.businesslogic.gateways.GameRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class RepositoriesConfiguration {

    @Bean
    @ConditionalOnProperty("game.card.inmemory")
    public GameRepository gameRepository() {
        return new InMemoryGameRepository();
    }
}
