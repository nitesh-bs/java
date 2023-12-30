package com.nitesh.springdemo.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students;
	
	@PostConstruct
	public void loadData() {
	
		students = new ArrayList<>();		
		students.add(new Student("nitesh", "shekhada"));
		students.add(new Student("rahul", "patel"));
		students.add(new Student("raj", "shah"));
		
	}
	

	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		if((studentId >= students.size()) || studentId < 0) {
			throw new StudentNotFoundException("Student id not fount --- "+studentId);
		}
		return students.get(studentId);
	}
	

	
}
