package com.nitesh.GraphNeo4jDemo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.nitesh.GraphNeo4jDemo.model.Book;

public interface BookRepository extends Neo4jRepository<Book, Long> {

  Book findByTitle(String title);

  Book findByLanguage(String language);

        @Query("MATCH (b:Book) RETURN b")        
        List<Book> getAllBooks();

  @Query("MATCH (b:Book) WHERE b.title =~ ('(?i).*'+$str+'.*') RETURN b")
  List<Book> findByTitleContaining(String str);
}