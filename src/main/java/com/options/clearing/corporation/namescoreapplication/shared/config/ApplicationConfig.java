package com.options.clearing.corporation.namescoreapplication.shared.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Value("#{${score.map}}")
    private Map<Character, Integer> scoreMap;

    public Map<Character, Integer> getScoreMap() {
        //The letter T is manually added here as SpEL cannot inject T from external configuration
        scoreMap.put('T', 20);
        return scoreMap;
    }

    public void invokeProgramTermination() {
        System.exit(0);
    }
}
