package com.nitesh.springBootReactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	//@Test
	public void fluxTest() {
		Flux<String> stringFlux = Flux.just("Spring","Spring boot","Reactive Spring")
			//	.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.log();
		
		stringFlux.subscribe(
				System.out::println,
				(e)->System.err.println(e)
				);
	}
	
	//@Test
	public void fluxTestElements() {
		Flux<String> stringFlux = Flux.just("Spring","Spring boot","Reactive Spring")
				.log();
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Spring boot")
		.expectNext("Reactive Spring")
		.verifyComplete()
		;
		
		
	}
	
	//@Test
	public void fluxTestElements_Error() {
		Flux<String> stringFlux = Flux.just("Spring","Spring boot","Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.log();
		StepVerifier.create(stringFlux)
		.expectNext("Spring")
		.expectNext("Spring boot")
		.expectNext("Reactive Spring")
		//.expectError(RuntimeException.class)
		.expectErrorMessage("Exception Occurred")
		.verify()
		;
		
	}
	
	
	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Spring");
		StepVerifier.create(stringMono.log())
		.expectNext("Spring")
		.verifyComplete();
		
	}
	
}
