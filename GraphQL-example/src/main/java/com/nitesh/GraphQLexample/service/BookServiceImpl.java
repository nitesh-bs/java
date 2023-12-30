package com.nitesh.GraphQLexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.GraphQLexample.entity.Book;
import com.nitesh.GraphQLexample.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book findByBookId(int bookId) {
		 return bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book id is not available!!!"));
	}

}
