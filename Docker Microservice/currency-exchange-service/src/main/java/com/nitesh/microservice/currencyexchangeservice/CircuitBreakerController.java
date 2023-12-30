package com.nitesh.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "default",fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	//10s -> 10000 calls to the sample api (RateLimit)
	 public String sampleApi() {
		logger.info("Sample Api call recevied");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	
	public String hardcodedResponse(Exception exception) {
		return "fallback hardcodedResponse";
	}
}
