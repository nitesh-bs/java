package com.nitesh.product.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_description")
	private String productDescription;
	
	@Column(name = "product_file_name")
	private String productFileName;
	
	
	@Column(name = "product_category")
	private String productCategory;
	
	@Column(name = "product_stock")
	private int productStock;
	
	
	
	@Column(name = "product_price")
	private Double productPrice;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Product() {
		
	}

	

	public Product(String productName, String productDescription, String productFileName, String productCategory,
			int productStock, Double productPrice) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.productFileName = productFileName;
		this.productCategory = productCategory;
		this.productStock = productStock;
		this.productPrice = productPrice;
	}

	


	public Product(int id, String productName, String productDescription, String productFileName,
			String productCategory, int productStock, Double productPrice, Category category) {
		this.id = id;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productFileName = productFileName;
		this.productCategory = productCategory;
		this.productStock = productStock;
		this.productPrice = productPrice;
		this.category = category;
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

	

	

	public String getProductFileName() {
		return productFileName;
	}



	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}
	
	@Transient
	public String getProductFile() {
		if(productFileName == null ) return null;
		return "/uploads/"+productFileName;
	}

	


	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", productFileName=" + productFileName + ", productCategory=" + productCategory + ", productStock="
				+ productStock + ", productPrice=" + productPrice + ", category=" + category + "]";
	}
	
	
	
}
