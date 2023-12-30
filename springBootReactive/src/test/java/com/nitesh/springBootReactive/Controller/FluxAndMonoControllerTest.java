package com.nitesh.springBootReactive.Controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void flux_approch1() {
		Flux<Integer> intFlux = webTestClient.get().uri("/flux")
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(intFlux)
		.expectSubscription().expectNext(1,2,3,5)
		.verifyComplete();
	}
	
	
	@Test
	public void flux_approch2() {
		
		 webTestClient.get().uri("/flux")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Integer.class)
				.hasSize(4);
				
	}
	
	@Test
	public void flux_approach3() {
		List<Integer> expectedList = java.util.Arrays.asList(1,2,3,5) ;
		
		EntityExchangeResult<List<Integer>> intFluxEntityExchangeResult = webTestClient.get().uri("/flux")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Integer.class)
		.returnResult();
		
		assertEquals(expectedList, intFluxEntityExchangeResult.getResponseBody());
		
	}
	
	@Test
	public void flux_approach4() {
		List<Integer> expectedList = java.util.Arrays.asList(1,2,3,5) ;
		
		webTestClient.get().uri("/flux")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Integer.class)
		.consumeWith((res)->{
			assertEquals(expectedList, res.getResponseBody());
		});
		
	}
	
	@Test
	public void flux_strea() {
		Flux<Long> longFlux = webTestClient.get().uri("/fluxStream")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Long.class)
				.getResponseBody();
		
		StepVerifier.create(longFlux)
		.expectNext(0l,1l,2l)
		.thenCancel()
		.verify();
	}
	
	@Test
	public void mono() {
		webTestClient.get().uri("/mono")
		.accept(MediaType.APPLICATION_JSON)
		.exchange().expectStatus().isOk()
		.expectBody(Integer.class)
		.consumeWith((res)->{
			assertEquals(new Integer(1), res.getResponseBody());
		});
		
	}
}
