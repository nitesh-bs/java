package com.nitesh.product.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nitesh.product.entity.Category;
import com.nitesh.product.entity.Product;

public interface ProductPaginationDao extends PagingAndSortingRepository<Product, Integer> {
//	SELECT o FROM PurchaseOrder o JOIN Item i ON o.id = i.order.id WHERE i.id
	@Query("select distinct p from Product p where p.category.categoryStatus = 'A'")
	public Page<Product> findAllActiveProducts(Pageable pageable);

	
	@Query("select distinct p from Product p where p.category.categoryStatus = 'A' and p.productName LIKE %:pName%  or p.productDescription LIKE %:pDescription%")
	public List<Product> findByProductNameContainsOrProductDescriptionContainsAllIgnoreCase(@Param("pName") String pName,
			@Param("pDescription") String pDescription);
	
}
