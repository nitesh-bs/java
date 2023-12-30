package com.nitesh.product.service;

import java.util.List;

import com.nitesh.product.entity.Category;

public interface CategoryService {

	public List<Category> findAllCategory();
	
	public Category findCategoryByCategoryId(int id);
	
	public void saveCategory(Category category);
	
	public void deleteCategory(int id);
	
}
