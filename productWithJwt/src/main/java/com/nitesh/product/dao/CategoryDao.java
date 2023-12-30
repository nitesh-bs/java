package com.nitesh.product.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nitesh.product.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	@Query("from Category c where c.categoryStatus = 'A'")
	public List<Category> findAllActiveCategory();
	

}
