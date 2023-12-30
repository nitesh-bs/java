package com.nitesh.h2Database.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nitesh.h2Database.entity.Course;

@Repository
public class CourseJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String insert_query =
			"""
			insert into course(id,name,author) values (?,?,?);					
			""";
	
	public void insertCourse(Course course) {
		jdbcTemplate.update(insert_query,course.getId(),course.getName(),course.getAuthor());
	}
	
	public void deleteCourseById(int id) {
		jdbcTemplate.update("delete from course where id = (?)",id);
	}
	
	public Course coursefindById(int id) {
		return jdbcTemplate.queryForObject("select * from course where id = (?)", new BeanPropertyRowMapper<>(Course.class),id);
		
	}
}
