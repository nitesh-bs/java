package com.nitesh.h2Database.course.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.h2Database.entity.CourseJPA;

public interface CourseSpringDataJpa extends JpaRepository<CourseJPA, Integer> {

	
	List<CourseJPA> findByAuthor(String author);
}
