package com.nitesh.GraphNeo4jDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
//@EnableNeo4jRepositories(
//		  basePackages = "com.nitesh.GraphNeo4jDemo.repository")
//@EntityScan(basePackages = {"com.nitesh.GraphNeo4jDemo.model"})
public class GraphNeo4jDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphNeo4jDemoApplication.class, args);
	}

}
