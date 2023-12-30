package com.nitesh.GraphQLexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nitesh.GraphQLexample.entity.Book;
import com.nitesh.GraphQLexample.service.BookService;

@SpringBootApplication
public class GraphQlExampleApplication implements CommandLineRunner{

	@Autowired
	private BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(GraphQlExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book b1 = new Book();
		b1.setTitle("Java book1");
		b1.setDesc("book1 description");
		b1.setPages(100);
		b1.setPrice(200);
		b1.setAuthor("abc");
		
		Book b2 = new Book();
		b2.setTitle("Java book2");
		b2.setDesc("book2 description");
		b2.setPages(102);
		b2.setPrice(202);
		b2.setAuthor("pqr");
		
		Book b3 = new Book();
		b3.setTitle("Java book3");
		b3.setDesc("book3 description");
		b3.setPages(103);
		b3.setPrice(203);
		b3.setAuthor("xyz");
		
		bookService.create(b1);
		bookService.create(b2);
		bookService.create(b3);
		
	}

}
