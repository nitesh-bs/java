package com.nitesh.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nitesh.product.dao.ProductDao;
import com.nitesh.product.dao.ProductPaginationDao;
import com.nitesh.product.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductPaginationDao productPaginationDao;

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAllActiveProducts();
	}

	@Override
	public Product findProductByProductId(int productId) {
		Optional<Product> findById = productDao.findById(productId);
		Product product = null;
		if (findById.isPresent()) {
			product = findById.get();
		} else {
			throw new RuntimeException("Did not find Product : " + productId);
		}
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		productDao.save(product);
	}

	@Override
	public void deleteProduct(int productId) {
		Optional<Product> findById = productDao.findById(productId);
		Product product = null;
		if (findById.isPresent()) {
			productDao.deleteById(productId);
		} else {
			throw new RuntimeException("Did not find Product : " + productId);
		}

	}

	@Override
	public Integer totalProduct() {
		return (int) productDao.count();
	}

	@Override
	public List<Product> searchByProduct(String productName) {
		List<Product> results = null;

		if (productName != null && (productName.trim().length() > 0)) {
			results = productDao.findByProductNameContainsOrProductDescriptionContainsAllIgnoreCase(productName,
					productName);
		} else {
			results = productDao.findAllActiveProducts();
		}
		return results;
	}

	@Override
	public Page<Product> findAllProductsWithPagination(int pageNumber, int size,Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, size,sort);
		return productPaginationDao.findAllActiveProducts(pageable);
	}

	@Override
	public Page<Product> findAllProductsWithPagination(int pageNumber, int size) {
		Pageable pageable = PageRequest.of(pageNumber - 1, size);
		return productPaginationDao.findAllActiveProducts(pageable);
	}

}
