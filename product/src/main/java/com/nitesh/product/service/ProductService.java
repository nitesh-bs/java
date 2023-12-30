package com.nitesh.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.nitesh.product.entity.Product;

public interface ProductService {

	public List<Product> findAllProducts();
	
	public Product findProductByProductId(int productId);
	
	public void saveProduct(Product product);
	
	public void deleteProduct(int productId);
	
	public Integer totalProduct();
	
	public List<Product> searchByProduct(String productName);
	
	public Page<Product> findAllProductsWithPagination(int pageNumber, int size, Sort sort);

	public Page<Product> findAllProductsWithPagination(int pageNumber, int size);


}
