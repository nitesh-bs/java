package com.nitesh.springBootDataRedis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springBootDataRedis.entity.Product;
import com.nitesh.springBootDataRedis.repository.ProductDao;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDao dao;
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return dao.save(product);
	}
	
	@GetMapping
	public List<Product> findAll(){
		return dao.findAll();
	}
	
	
	@GetMapping("/{id}")
	@Cacheable(key = "#id",value = "Product",unless = "#result.price>399")
	public Product findByProductId(@PathVariable int id) {
		return dao.findProductById(id);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id",value = "Product")
	public String remove(@PathVariable int id) {
		return dao.deleteProduct(id);
	}
	
	
}
