package com.nitesh.GraphNeo4jDemo.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.nitesh.GraphNeo4jDemo.model.Author;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {

  @Query("MATCH (au:Author)<-[a:AUTHORED]-(b:Book) RETURN au,a,b")
  List<Author> getAllAuthors();
  
}