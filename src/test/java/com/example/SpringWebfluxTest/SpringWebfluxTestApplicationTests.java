package com.example.SpringWebfluxTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class SpringWebfluxTestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	PruebaService pruebaService;

	@Test
	void testMono() {
		Mono<String> uno = pruebaService.buscarUno();
		StepVerifier.create(uno).expectNext("Pedro").verifyComplete();
	}
	@Test
	void testVarios() {
		Flux<String> uno = pruebaService.buscarTodos();
		StepVerifier.create(uno).expectNext("Pedro").expectNext("Maria").expectNext("Jesus").expectNext("Carmen").verifyComplete();
	}
}
