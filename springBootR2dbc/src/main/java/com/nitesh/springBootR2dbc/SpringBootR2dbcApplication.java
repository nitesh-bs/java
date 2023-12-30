package com.nitesh.springBootR2dbc;

import java.time.Duration;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
//import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
//import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
@EnableR2dbcRepositories
public class SpringBootR2dbcApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootR2dbcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootR2dbcApplication.class, args);
	}
	
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new 
				ResourceDatabasePopulator(new ClassPathResource("schema.sql"))
				);
		return initializer;
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args)->{
			
			//save new customers
			repository.saveAll(Arrays.asList(
					new Customer("Jack", "Bauer"),
	                new Customer("Chloe", "O'Brian"),
	                new Customer("Kim", "Bauer"),
	                new Customer("David", "Palmer"),
	                new Customer("Michelle", "Dessler")
					))
			.blockLast(Duration.ofSeconds(10));
			
			//find all customers
			LOGGER.info("FindAll Customers:::");
			repository.findAll().doOnNext(cus->{
				LOGGER.info(cus.toString());
			}).blockLast(Duration.ofSeconds(10));
			LOGGER.info("");
			
			//find by customer id
			repository.findById(1L).doOnNext(cus->{
				LOGGER.info("Find By CustomerId : "+cus.toString());
				LOGGER.info("");
			})
			.block(Duration.ofSeconds(10));
			
			//find by customer last name
			LOGGER.info("Find By Customer Last Name");
			repository.findByLastName("Bauer").doOnNext(bauer->{
				LOGGER.info(bauer.toString());
			}).blockLast(Duration.ofSeconds(10));
			LOGGER.info("");
		};
	}
	
}
