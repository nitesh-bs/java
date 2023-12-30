package com.example.demo;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nitesh.springReactiveMongoDb.SpringReactiveMongodbCrudApplication;
import com.nitesh.springReactiveMongoDb.controller.ProductController;
import com.nitesh.springReactiveMongoDb.dto.ProductDto;
import com.nitesh.springReactiveMongoDb.service.ProductService;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers =  ProductController.class)
@ContextConfiguration(classes = SpringReactiveMongodbCrudApplication.class)
public class MongoTestReactive {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ProductService service;
	
	
	@Test
	public void addProductTest() {
		Mono<ProductDto> productDtoMono= Mono.just(new ProductDto("101","mobile", 1, 1000));
		when(service.saveProduct(productDtoMono)).thenReturn(productDtoMono);
		
		webTestClient.post().uri("/products").body(Mono.just(productDtoMono),ProductDto.class)
		.exchange()
		.expectStatus().isOk();
	}

	@Test
	public void getProductsTest() {
		Flux<ProductDto> productDtoFlux=Flux.just(
				new ProductDto("101","mobile", 1, 1000)
				,new ProductDto("102","mobile",1,10000));
		
		when(service.getAllProducts()).thenReturn(productDtoFlux);
		
		Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNext(new ProductDto("101","mobile", 1, 1000))
		.expectNext(new ProductDto("102","mobile",1,10000))
		.verifyComplete();
		
	}
	


	@Test
	public void getProductTest(){
		Mono<ProductDto> productDtoMono=Mono.just(new ProductDto("102","mobile",1,10000));
		when(service.getProduct(any())).thenReturn(productDtoMono);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getName().equals("mobile"))
				.verifyComplete();
	}


	@Test
	public void updateProductTest(){
		Mono<ProductDto> productDtoMono=Mono.just(new ProductDto("102","mobile",1,10000));
		when(service.updateProduct(productDtoMono,"102")).thenReturn(productDtoMono);

		webTestClient.put().uri("/products/update/102")
				.body(Mono.just(productDtoMono),ProductDto.class)
				.exchange()
				.expectStatus().isOk();//200
	}

	@Test
	public void deleteProductTest(){
    	given(service.deleteProduct(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/products/delete/102")
				.exchange()
				.expectStatus().isOk();//200
	}
}
