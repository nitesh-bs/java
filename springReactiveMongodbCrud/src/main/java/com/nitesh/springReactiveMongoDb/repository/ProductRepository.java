package com.nitesh.springReactiveMongoDb.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nitesh.springReactiveMongoDb.dto.ProductDto;
import com.nitesh.springReactiveMongoDb.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

}
