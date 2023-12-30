package com.nitesh.product.controller.admin;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nitesh.product.entity.Category;
import com.nitesh.product.entity.Product;
import com.nitesh.product.model.ProductModel;
import com.nitesh.product.service.CategoryService;
import com.nitesh.product.service.FileStorageService;
import com.nitesh.product.service.ProductService;

@Controller
@RequestMapping("admin")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileStorageService fileStorageService;

	private Logger logger = Logger.getLogger(getClass().getName());
	private final String upload_dir = "uploads/";

//	Validate Each Request 
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/productPagination")
	public String findProductWithPagination(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "4") int size,
			@RequestParam(defaultValue = "id,asc", required = false) String[] sort, Model model) {

		String sortField = sort[0];
		String sortDirection = sort[1];

		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);

		if (pageNumber < 1) {
			pageNumber = 1;
		}

		Page<Product> page = productService.findAllProductsWithPagination(pageNumber, size, Sort.by(order));
		List<Product> products = page.getContent();

		long totalElements = page.getTotalElements();
		int sizes = page.getSize();
		int totalItems = page.getNumberOfElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("currentPage", page.getNumber() + 1);

		if (pageNumber > totalPages) {
			pageNumber = totalPages;
			page = productService.findAllProductsWithPagination(pageNumber, size, Sort.by(order));
			products = page.getContent();

			totalElements = page.getTotalElements();
			sizes = page.getSize();
			totalItems = page.getNumberOfElements();
			totalPages = page.getTotalPages();
		}

		model.addAttribute("product", products);
		model.addAttribute("add_product", new ProductModel());
		model.addAttribute("category", categoryService.findAllCategory());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		model.addAttribute("sizes", sizes);
		model.addAttribute("pageSize", size);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		return "admin/list-product";
	}

	@GetMapping("/products")
	public String findAllProducts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "4") int size,
			@RequestParam(defaultValue = "id,asc", required = false) String[] sort,
			@RequestParam("productId") Optional<Integer> productId, Model model) {

		if (productId.isPresent()) {
			Product product = productService.findProductByProductId(productId.get());

			if (product == null) {
				throw new RuntimeException("Product not found - " + productId);
			}
			ProductModel productModel = new ProductModel();
			productModel.setId(product.getId());
			productModel.setProductName(product.getProductName());
			productModel.setProductDescription(product.getProductDescription());
			productModel.setProductCategory(product.getProductCategory());
			productModel.setProductStock(product.getProductStock());
			productModel.setProductPrice(product.getProductPrice());
			productModel.setCategoryId(product.getCategory().getCategoryId());

			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			model.addAttribute("product", productService.findAllProducts());
			model.addAttribute("category", categoryService.findAllCategory());
			model.addAttribute("add_product", productModel);
			model.addAttribute("showModel", "yes");
			return "admin/list-product";
		}

		/*
		 * model.addAttribute("product", productService.findAllProducts());
		 * model.addAttribute("add_product", new ProductModel());
		 * model.addAttribute("category", categoryService.findAllCategory());
		 */

		String sortField = sort[0];
		String sortDirection = sort[1];

		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);

		if (pageNumber < 1) {
			pageNumber = 1;
		}

//		Page<Product> page = productService.findAllProductsWithPagination(pageNumber, size, Sort.by(order));
		Page<Product> page = productService.findAllProductsWithPagination(pageNumber, size);
		List<Product> products = page.getContent();

		long totalElements = page.getTotalElements();
		int sizes = page.getSize();
		int totalItems = page.getNumberOfElements();
		int totalPages = page.getTotalPages();
		model.addAttribute("currentPage", page.getNumber() + 1);

		if (pageNumber > totalPages) {
			pageNumber = totalPages;
//			page = productService.findAllProductsWithPagination(pageNumber, size, Sort.by(order));
			page = productService.findAllProductsWithPagination(pageNumber, size);
			products = page.getContent();

			totalElements = page.getTotalElements();
			sizes = page.getSize();
			totalItems = page.getNumberOfElements();
			totalPages = page.getTotalPages();
		}

//		List<Product> modifiableList = new ArrayList<Product>(products);
		/*
		 * if (direction.equals(Sort.Direction.DESC)) {
		 * 
		 * Comparator<Product> compareById = (Product o1, Product o2) ->
		 * o1.getProductName() .compareTo(o2.getProductName());
		 * Collections.sort(modifiableList, compareById.reversed());
		 * 
		 * model.addAttribute("bySalary",
		 * Comparator.comparing(Product::getProductName).reversed()); } else {
		 * model.addAttribute("bySalary",
		 * Comparator.comparing(Product::getProductName)); }
		 * 
		 * 
		 * <tr th:each="prod : ${#lists.sort(product, bySalary)}">
		 * 
		 */

		switch (direction) {
		case DESC:
			switch (sortField) {
			case "productName":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductName).reversed());
				break;
			case "productDescription":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductDescription).reversed());
				break;
			case "productStock":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductStock).reversed());
				break;
			default:
				model.addAttribute("bySort", Comparator.comparing(Product::getId).reversed());
				break;
			}
			break;
		case ASC:
			switch (sortField) {
			case "productName":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductName));
				break;
			case "productDescription":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductDescription));
				break;
			case "productStock":
				model.addAttribute("bySort", Comparator.comparing(Product::getProductStock));
				break;
			default:
				model.addAttribute("bySort", Comparator.comparing(Product::getId));
				break;
			}
			break;

		default:
			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			break;
		}

		model.addAttribute("product", products);
		model.addAttribute("add_product", new ProductModel());
		model.addAttribute("category", categoryService.findAllCategory());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		model.addAttribute("sizes", sizes);
		model.addAttribute("pageSize", size);

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		return "admin/list-product";
	}

	@GetMapping("/addProducts")
	public String addProducts(Model model) {
		ProductModel productModel = new ProductModel();
		model.addAttribute("product", productModel);
		model.addAttribute("category", categoryService.findAllCategory());
		return "admin/products";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("productId") int productId, Model model) {

		Product product = productService.findProductByProductId(productId);

		if (product == null) {
			throw new RuntimeException("Product not found - " + productId);
		}
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setProductName(product.getProductName());
		productModel.setProductDescription(product.getProductDescription());
		productModel.setProductCategory(product.getProductCategory());
		productModel.setProductStock(product.getProductStock());
		productModel.setProductPrice(product.getProductPrice());
		productModel.setCategoryId(product.getCategory().getCategoryId());

		model.addAttribute("product", productModel);
		model.addAttribute("category", categoryService.findAllCategory());
		
		return "admin/products";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("productId") int productId) {

		Product product = productService.findProductByProductId(productId);

		if (product == null) {
			throw new RuntimeException("Product not found - " + productId);
		}

		productService.deleteProduct(productId);

		return "redirect:/admin/products";
	}

	@PostMapping("/addProducts")
	public String saveProducts(@Valid @ModelAttribute("product") ProductModel productModel, BindingResult bindingResult,
			Model model) {

		
		logger.info("product model is :" + productModel + " : " + bindingResult.getAllErrors());

		MultipartFile file = productModel.getFile();
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a file to Upload");
			model.addAttribute("category", categoryService.findAllCategory());
			
			return "admin/products";
		}

		String fileExtention = fileStorageService.getFileExtension(file.getOriginalFilename());

		System.out.print("file extension : " + fileExtention);

		if (fileExtention.equals("jpeg") || fileExtention.equals("jpg")) {

		} else {
			model.addAttribute("message", "Invalid File type");
			model.addAttribute("category", categoryService.findAllCategory());
			return "admin/products";
		}

		if (bindingResult.hasErrors()) {
			productModel.setFile(null);
			System.out.println("product name error======");

			model.addAttribute("product", productModel);
			model.addAttribute("category", categoryService.findAllCategory());
			return "admin/products";

		}

		String fileName = fileStorageService.storeFile(file);

		Product product = new Product();
		product.setId(productModel.getId());
		product.setProductName(productModel.getProductName());
		product.setProductDescription(productModel.getProductDescription());
		product.setProductCategory(productModel.getProductCategory());
		product.setProductStock(productModel.getProductStock());
		product.setProductPrice(productModel.getProductPrice());
		product.setProductFileName(fileName);

		// Get category from category id
		Category category = categoryService.findCategoryByCategoryId(productModel.getCategoryId());

		if (category == null) {
			model.addAttribute("product", productModel);
			model.addAttribute("category", categoryService.findAllCategory());
			return "admin/products";
		}

		product.setCategory(category);

		productService.saveProduct(product);
		model.addAttribute("message", "You successfully uploades " + fileName);

		return "redirect:/admin/products";
	}

	@PostMapping("/products")
	public String saveProductsModel(@Valid @ModelAttribute("add_product") ProductModel productModel,
			BindingResult bindingResult, Model model) {

		logger.info("product model is :" + productModel + " : " + bindingResult.getAllErrors());
		MultipartFile file = productModel.getFile();
		if (file.isEmpty()) {
			model.addAttribute("product", productService.findAllProducts());
			model.addAttribute("category", categoryService.findAllCategory());
			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			model.addAttribute("message", "Please select a file to Upload");
			model.addAttribute("showModel", "yes");

			return "admin/list-product";
		}

		String fileExtention = fileStorageService.getFileExtension(file.getOriginalFilename());
		if (fileExtention.equals("jpeg") || fileExtention.equals("jpg")) {

		} else {
			model.addAttribute("product", productService.findAllProducts());
			model.addAttribute("category", categoryService.findAllCategory());
			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			model.addAttribute("message", "Invalid File type");
			model.addAttribute("showModel", "yes");
			return "admin/list-product";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("showModel", "yes");
			model.addAttribute("product", productService.findAllProducts());
			model.addAttribute("category", categoryService.findAllCategory());
			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			productModel.setFile(null);
			System.out.println("product name error======");
			return "admin/list-product";

		}

		/*
		 * String fileExtension =
		 * fileStorageService.getFileExtension(file.getOriginalFilename());
		 * System.out.println("fileExtension : "+fileExtension);
		 * 
		 * if(fileExtension.equals(".jpeg") || fileExtension.equals("jpg")) {
		 * 
		 * } else { model.addAttribute("message", "File must be jpeg,jpg to Upload");
		 * 
		 * return "admin/products"; }
		 */

		String fileName = fileStorageService.storeFile(file);

		/*
		 * String fileName =
		 * org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		 * 
		 * try { if (!(new File(upload_dir).exists())) { File file2 = new
		 * File(upload_dir);
		 * 
		 * System.out.println("file name : " + file2.getAbsolutePath()); } Path path =
		 * Paths.get(upload_dir + fileName);
		 * 
		 * Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		Product product = new Product();
		product.setId(productModel.getId());
		product.setProductName(productModel.getProductName());
		product.setProductDescription(productModel.getProductDescription());
		product.setProductCategory(productModel.getProductCategory());
		product.setProductStock(productModel.getProductStock());
		product.setProductPrice(productModel.getProductPrice());
		product.setProductFileName(fileName);

		Category category = categoryService.findCategoryByCategoryId(productModel.getCategoryId());

		if (category == null) {
			model.addAttribute("showModel", "yes");
			model.addAttribute("product", productService.findAllProducts());
			model.addAttribute("category", categoryService.findAllCategory());
			model.addAttribute("bySort", Comparator.comparing(Product::getId));
			productModel.setFile(null);
			return "admin/products";
		}

		product.setCategory(category);

		productService.saveProduct(product);
		model.addAttribute("message", "You successfully uploades " + fileName);
		model.addAttribute("showModel", null);
		return "redirect:/admin/products";
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/searchProducts")
	public String search(@RequestParam("productName") String productName, Model model) {

		model.addAttribute("product", productService.searchByProduct(productName));
		model.addAttribute("add_product", new ProductModel());
		model.addAttribute("category", categoryService.findAllCategory());
		model.addAttribute("bySort", Comparator.comparing(Product::getId));
		return "admin/list-product";
	}
}
