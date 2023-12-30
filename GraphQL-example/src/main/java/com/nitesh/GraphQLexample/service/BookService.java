package com.nitesh.GraphQLexample.service;

import java.util.List;

import com.nitesh.GraphQLexample.entity.Book;

public interface BookService {

	Book create(Book book);
	
	List<Book> findAllBooks();
	
	Book findByBookId(int bookId);
}
