package com.nitesh.springboot.cruddemo;

import com.nitesh.springboot.cruddemo.dao.StudentDAO;
import com.nitesh.springboot.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			System.out.println("Hello World!!!");
//			createStudent(studentDAO);
//			createMultipleStudent(studentDAO);
//			findStudentById(studentDAO);
			findAllStudent(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);

		};
	}

	private void deleteStudent(StudentDAO studentDAO) {

		studentDAO.delete(11);
		System.out.printf("Delete student : ");
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId =11;
		Student student=studentDAO.findById(studentId);
		student.setFirstName("meet");
		studentDAO.update(student);
		System.out.println("Updated student : "+student);
	}

	private void findAllStudent(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAllStudents();
		for (Student s :
				students) {
			System.out.println(s);
		}
	}

	private void findStudentById(StudentDAO studentDAO) {
		Student student = studentDAO.findById(1);
		System.out.println(student);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		Student student1 = new Student("abc","Patel","mpat@gmail.com");
		Student student2 = new Student("raj","Patel","abcdt@gmail.com");

		studentDAO.save(student1);
		studentDAO.save(student2);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Create Student");
		Student student = new Student("Nayan","Patel","mpat@gmail.com");

		studentDAO.save(student);

		System.out.println("Saved Student Id :"+student.getStudId());
	}
}
