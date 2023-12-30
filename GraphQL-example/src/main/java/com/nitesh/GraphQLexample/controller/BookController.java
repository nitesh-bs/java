package com.nitesh.GraphQLexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nitesh.GraphQLexample.entity.Book;
import com.nitesh.GraphQLexample.service.BookService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@QueryMapping("allBooks")
	public List<Book> findAllBooks(){
		return bookService.findAllBooks();
	}
	
	@MutationMapping("createBook")
	public Book saveBook(@Argument BookInput book) {
		Book b= new Book( book.getTitle(), book.getDesc(), book.getAuthor(), book.getPrice(), book.getPages());
		return bookService.create(b);
	}
	
	@QueryMapping("getBook")
	Book findBookById(@Argument Integer bookId) {
		return bookService.findByBookId(bookId);
	}
}
