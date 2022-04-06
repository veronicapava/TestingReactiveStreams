package com.example.SpringWebfluxTest;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PruebaService {
    public Mono<String> buscarUno() {
        return Mono.just("Pedro");
    }
    public Flux<String> buscarTodos() {
        return Flux.just("Pedro", "Maria", "Jesus", "Carmen");
    }
    public Flux<String> buscarTodosLento() {
        return Flux.just("Pedro", "Maria", "Jesus", "Carmen").delaySequence(Duration.ofSeconds(20));
    }
}
