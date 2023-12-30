package com.nitesh.springBootDataRedis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.nitesh.springBootDataRedis.entity.Product;

@Repository
public class ProductDao {

	public static final String HASH_KEY= "Product";
	private RedisTemplate template;
	
	@Autowired
	public ProductDao(RedisTemplate redisTemplate) {
		this.template = redisTemplate;
	}
	
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(),product);
		return product;
	}
	
	public List<Product> findAll(){
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Product findProductById(int id) {
		
		System.out.println("call from db :"+id);
		return (Product) template.opsForHash().get(HASH_KEY, id); 
	}
	
	public String deleteProduct(int id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "Product deleted successfully";
	}
}
