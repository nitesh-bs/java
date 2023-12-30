package com.nitesh.restwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nitesh.restwebservices.bean.Post;
import com.nitesh.restwebservices.bean.User;
import com.nitesh.restwebservices.dao.PostRepository;
import com.nitesh.restwebservices.dao.UserRepository;
import com.nitesh.restwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("jpa")
public class UserJpaController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public EntityModel<User> findOneUser(@PathVariable int userId) {
		Optional<User> findOne = repository.findById(userId);
		if(findOne.isEmpty()) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		
		EntityModel<User> entityModel = EntityModel.of(findOne.get());
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUsers());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		 User savedUser = repository.save(user);
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{userId}")
				 .buildAndExpand(savedUser.getId())
				 .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		Optional<User> findOne = repository.findById(userId);
		if(findOne.isEmpty()) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		repository.deleteById(userId);
		
	}
	
	@GetMapping("/users/{userId}/posts")
	public List<Post> findPostOfUser(@PathVariable int userId) {
		Optional<User> findOne = repository.findById(userId);
		if(findOne.isEmpty()) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		return findOne.get().getPosts();		
	}
	
	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Post> createPostOfUser(@PathVariable int userId,@Valid @RequestBody Post post) {
		Optional<User> findOne = repository.findById(userId);
		if(findOne.isEmpty()) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		
		post.setUser(findOne.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{userId}")
				 .buildAndExpand(savedPost.getId())
				 .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{userId}/posts/{postId}")
	public EntityModel<Post> findOnePost(@PathVariable int userId,@PathVariable int postId) {
		Optional<User> findOne = repository.findById(userId);
		if(findOne.isEmpty()) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		
		Optional<Post> findPost = postRepository.findById(postId);
		if(findPost.isEmpty()) {
			throw new UserNotFoundException("Post not found id - "+userId);
		}
		EntityModel<Post> entityModel = null;
		Optional<Post> findUserPost = postRepository.findByIdAndUserId(postId,userId);
		if(findUserPost.isEmpty()) {
			throw new UserNotFoundException("User don't have Post id - "+postId);
		}else {
			entityModel = EntityModel.of(findPost.get());	
		}
		
		
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUsers());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;
	}
	
}
