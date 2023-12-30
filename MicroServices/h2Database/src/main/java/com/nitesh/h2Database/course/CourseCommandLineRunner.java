package com.nitesh.h2Database.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nitesh.h2Database.course.jpa.CoruseJpaRepository;
import com.nitesh.h2Database.course.jpa.CourseSpringDataJpa;
import com.nitesh.h2Database.entity.Course;
import com.nitesh.h2Database.entity.CourseJPA;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//	@Autowired
//	private CourseJdbcRepository jpaRepository;
	
//	@Autowired
//	private CoruseJpaRepository jpaRepository;
	
	@Autowired
	private CourseSpringDataJpa jpaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		/*
		 * jpaRepository.insertCourse(new Course(1,"Yash","Aws"));
		 * jpaRepository.insertCourse(new Course(2,"Raj","Python"));
		 * jpaRepository.insertCourse(new Course(3,"Kishan","Java"));
		 * 
		 * jpaRepository.deleteCourseById(2);
		 * 
		 * System.out.println(jpaRepository.coursefindById(1));
		 * System.out.println(jpaRepository.coursefindById(3));
		 */
		
		/*
		 * jpaRepository.insertCourseJpa(new CourseJPA(1,"Yash","Aws JPA"));
		 * jpaRepository.insertCourseJpa(new CourseJPA(2,"Raj","Python"));
		 * jpaRepository.insertCourseJpa(new CourseJPA(3,"Kishan","Java"));
		 * 
		 * jpaRepository.deleteById(2);
		 * 
		 * System.out.println(jpaRepository.findById(1));
		 * System.out.println(jpaRepository.findById(3));
		 */
		
		jpaRepository.save(new CourseJPA(1,"Yash","Aws JPA"));
		jpaRepository.save(new CourseJPA(2,"Raj","Python"));
		jpaRepository.save(new CourseJPA(3,"Kishan","Java"));
		
		jpaRepository.deleteById(2);
		
		System.out.println(jpaRepository.findById(1));
		System.out.println(jpaRepository.findById(3));
		System.out.println(jpaRepository.findAll());
		System.out.println(jpaRepository.count());
		System.out.println(jpaRepository.findByAuthor("JAVA"));
	}

}
