package com.nitesh;

import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.cassandradataloader.connection.DataStaxAstraProperties;

@SpringBootApplication
@RestController
@EnableCassandraRepositories
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class SpringGitHubLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGitHubLoginApplication.class, args);
	}

	@RequestMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("login");
	}
	
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
