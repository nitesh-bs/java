package com.nitesh.GraphNeo4jDemo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.GraphNeo4jDemo.model.Book;
import com.nitesh.GraphNeo4jDemo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping("/add")
  public Book addBook(@RequestBody Book book) {
    return bookService.saveBook(book);
  }

  @GetMapping("/{title}")
  public Book getBookByTitle(@PathVariable String title) {
    return bookService.getBookByTitle(title);
  }
  
  @GetMapping("/title/{str}")
  public List getBookByTitleContaining(@PathVariable String str) {
    return bookService.getBookByTitleContaining(str);
  }

  @GetMapping
  public Collection getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/count")
  public Long getCountofBooks() {
    return bookService.getCountOfBooks();
  }

  @DeleteMapping("/{id}")
  public String deleteBookById(@PathVariable String id) {
    bookService.deleteBook(Long.parseLong(id));
    return "Book deleted successfully";
  }

  @DeleteMapping
  public String deleteAllBooks() {
    bookService.deleteAllBooks();
    return "All Books deleted successfully";
  }

}