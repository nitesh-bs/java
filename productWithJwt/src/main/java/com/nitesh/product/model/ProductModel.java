package com.nitesh.product.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.nitesh.product.entity.Category;

public class ProductModel {

	private int id;

	@NotNull(message = "Product Name must be required!")
	@Size(min = 1, message = "is required")
	private String productName;

	@NotNull(message = "Product Description must be required!")
	@Size(min = 1, message = "is required")
	private String productDescription;

	@NotNull(message = "Category must be required!")
	@Size(min = 1, message = "is required")
	private String productCategory;

	@NotNull(message = "Stock must be required!")
	@Min(value = 1, message = "Minimum Stock is 1.")
	@Max(value = 500, message = "Maximum Stock is 500")
	private int productStock;

	@NotNull(message = "Product price must required!")
	private Double productPrice;

	@NotNull(message = "File must be required!")
	private MultipartFile file;

	@Min(value = 1, message = "Select Valid Category")
	private int categoryId;

	public ProductModel() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", productCategory=" + productCategory + ", productStock=" + productStock + ", productPrice="
				+ productPrice + ", file=" + file + ", categoryId=" + categoryId + "]";
	}

}
