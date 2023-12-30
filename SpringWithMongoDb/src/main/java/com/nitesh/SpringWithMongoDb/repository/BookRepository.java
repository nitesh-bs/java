package com.nitesh.SpringWithMongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nitesh.SpringWithMongoDb.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
