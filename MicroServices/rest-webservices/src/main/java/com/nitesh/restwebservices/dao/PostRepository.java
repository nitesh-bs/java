package com.nitesh.restwebservices.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitesh.restwebservices.bean.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public Optional<Post> findByIdAndUserId(int postId,int userId);
}
