package com.nitesh.GraphQLexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.GraphQLexample.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
