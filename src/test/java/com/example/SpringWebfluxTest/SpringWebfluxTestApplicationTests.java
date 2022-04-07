package com.example.SpringWebfluxTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

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

	@Test
	void testVariosLento() {
		Flux<String> uno = pruebaService.buscarTodosLento();
		StepVerifier.create(uno)
				.expectNext("Pedro")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("Maria")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("Jesus")
				.thenAwait(Duration.ofSeconds(1))
				.expectNext("Carmen")
				.thenAwait(Duration.ofSeconds(1)).verifyComplete();
	}

	@Test
	void testTodosFiltro() {
		Flux<String> source = pruebaService.buscarTodosFiltro();
		StepVerifier
				.create(source)
				.expectNext("JOHN")
				.expectNextMatches(name -> name.startsWith("MA"))
				.expectNext("CLOE", "CATE")
				.expectComplete()
				.verify();
	}

	@Test
	void testTodosFiltro2() {
		Flux<String> source = pruebaService.buscarTodosFiltro();
		StepVerifier
				.create(source)
				.expectNextCount(4)
				.expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
						throwable.getMessage().equals("Mensaje de Error")
				).verify();
	}

}
