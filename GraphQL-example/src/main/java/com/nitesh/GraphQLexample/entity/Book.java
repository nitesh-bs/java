package com.nitesh.GraphQLexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String desc;
	private String author;
	private double price;
	private int pages;
	
	public Book() {
		
	}
	
	
	public Book(String title, String desc, String author, double price, int pages) {
		this.title = title;
		this.desc = desc;
		this.author = author;
		this.price = price;
		this.pages = pages;
	}


	public Book(int id, String title, String desc, String author, double price, int pages) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.author = author;
		this.price = price;
		this.pages = pages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", desc=" + desc + ", author=" + author + ", price=" + price
				+ ", pages=" + pages + "]";
	}
	
	
	
}
