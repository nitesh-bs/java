package com.nitesh.springReactiveMongoDb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.nitesh.springReactiveMongoDb.dto.ProductDto;
import com.nitesh.springReactiveMongoDb.entity.Product;
import com.nitesh.springReactiveMongoDb.repository.ProductRepository;
import com.nitesh.springReactiveMongoDb.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Flux<ProductDto> getAllProducts(){
		return productRepository.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> getProduct(String id){
		return productRepository.findById(id).map(AppUtils::entityToDto);
	}
	
	public Flux<ProductDto> getProductInRange(double min,double max){
		return productRepository.findByPriceBetween(Range.closed(min,max));
	}
	
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
		return productDtoMono.map(AppUtils::dtoToEntity)
		.flatMap(productRepository::insert)
		.map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){

		return productRepository.findById(id)
				.flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
				.doOnNext(e->e.setId(id)))
				.flatMap(productRepository::save)
				.map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteProduct(String id){
		return productRepository.deleteById(id);
	}
	
	
}
