package com.nitesh.h2Database.course.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.h2Database.entity.Course;
import com.nitesh.h2Database.entity.CourseJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CoruseJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;


	public void insertCourseJpa(CourseJPA course) {
		entityManager.merge(course);
	}
	
	public CourseJPA findById(int id) {
		return entityManager.find(CourseJPA.class, id);
	}
	
	public void deleteById(int id) {
		CourseJPA courseJPA =entityManager.find(CourseJPA.class, id);
		entityManager.remove(courseJPA);
	}
}
