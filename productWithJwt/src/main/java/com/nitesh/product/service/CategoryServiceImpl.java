package com.nitesh.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.product.dao.CategoryDao;
import com.nitesh.product.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> findAllCategory() {
		return categoryDao.findAllActiveCategory();
	}

	@Override
	public Category findCategoryByCategoryId(int id) {
		Optional<Category> findById = categoryDao.findById(id);
		
		Category category = null;
		if(findById.isPresent()) {
			category = findById.get();
		}
		
		return category;
	}

	@Override
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}

	@Override
	public void deleteCategory(int id) {
		Optional<Category> findById = categoryDao.findById(id);
		if(findById.isPresent()) {
			categoryDao.deleteById(id);	
		}		
	}

}
