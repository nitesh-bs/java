package com.nitesh.springBootReactive.Handler;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SampleHandlerFunctionTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void flux_approch1() {
		Flux<Integer> intFlux = webTestClient.get().uri("/functional/flux")
		.exchange()
		.expectStatus().isOk()
		.returnResult(Integer.class)
		.getResponseBody();
		
		StepVerifier.create(intFlux)
		.expectSubscription().expectNext(1,2,3,4)
		.verifyComplete();
	}
	
	@Test
	public void mono() {
		webTestClient.get().uri("/functional/mono")
		.accept(MediaType.APPLICATION_JSON)
		.exchange().expectStatus().isOk()
		.expectBody(Integer.class)
		.consumeWith((res)->{
			assertEquals(new Integer(1), res.getResponseBody());
		});
		
	}
}
