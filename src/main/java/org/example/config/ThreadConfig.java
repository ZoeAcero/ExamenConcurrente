package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {

    @Bean
    public ExecutorService energyExecutor() {
        // Configuramos un pool de hilos fijo para la central

        return Executors.newFixedThreadPool(8);
    }
}