package com.nitesh.springBootwebfluxdemo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
//
//	@Test
//	public void testMono() {
//		// MONO only handle one element
//		Mono<?> monoString  = Mono.just("nitesh")
//				.then(Mono.error(new RuntimeException("Exception !!!")))
//				.log() ;
//		monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
//	}
	
	@Test
	public void testFlux() {
		Flux<String> fluxString = Flux.just("Spring","Nitesh","Db","Microservices")
				.concatWithValues("ghj")
				.concatWith(Flux.error(new RuntimeException("Flux Exception!")))
				.log();
		fluxString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
	}
}
