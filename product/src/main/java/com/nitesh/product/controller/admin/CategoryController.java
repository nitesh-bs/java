package com.nitesh.product.controller.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitesh.product.entity.Category;
import com.nitesh.product.service.CategoryService;

@Controller
@RequestMapping("admin")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/category")
	public String findAllCategory(@RequestParam("categoryId") Optional<Integer> categoryId,Model model) {
		if(categoryId.isPresent()) {
			Category category = categoryService.findCategoryByCategoryId(categoryId.get());
			
			if(category == null) {
				throw new RuntimeException("Did not find category - "+categoryId.get());
			}
			model.addAttribute("category",categoryService.findAllCategory());
			model.addAttribute("add_category",category);
			model.addAttribute("showModel", "yes");
			return "admin/category-list";
			
		}
		
		model.addAttribute("category",categoryService.findAllCategory());
		model.addAttribute("add_category",new Category());
		return "admin/category-list";
	}
	
	@PostMapping("/category")
	public String saveOrUpdateCategory(@Valid @ModelAttribute("add_category") Category category,BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("category",categoryService.findAllCategory());
			model.addAttribute("showModel", "yes");
			return "admin/category-list";
		}
		
		category.setCategoryStatus("A");
		categoryService.saveCategory(category);
		
		return "redirect:/admin/category";
	}
	
	@GetMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("categoryId") int categoryId) {
		Category category = categoryService.findCategoryByCategoryId(categoryId);
		
		if(category == null) {
			throw new RuntimeException("Did not find category - "+categoryId);
		}
		
		category.setCategoryStatus("D");
		
		categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}
}
