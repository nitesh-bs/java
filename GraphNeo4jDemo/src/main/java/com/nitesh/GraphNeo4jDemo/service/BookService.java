package com.nitesh.GraphNeo4jDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.GraphNeo4jDemo.model.Book;
import com.nitesh.GraphNeo4jDemo.repository.BookRepository;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;
  
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }
  
  public Book getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

        public List<Book> getBookByTitleContaining(String str) {
    return bookRepository.findByTitleContaining(str);
  }
  
  public Book getBooksByLanguage(String language) {
    return bookRepository.findByLanguage(language);
  }
  
  public List<Book> getAllBooks(){
    return bookRepository.getAllBooks();
  }
  
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }
  
  public void deleteAllBooks() {
    bookRepository.deleteAll();
  }
  
  public Long getCountOfBooks() {
    return bookRepository.count();
  }
}