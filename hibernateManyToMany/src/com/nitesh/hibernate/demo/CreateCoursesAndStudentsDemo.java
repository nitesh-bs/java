package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Review;
import com.nitesh.hibernate.demo.entity.Student;
import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take
																							// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {

			
			session.beginTransaction();

			Course course = new Course("This is Spring Course");
			
			session.save(course);
			System.out.println("saved course : "+course);
			
			
			Student student1 = new Student("nitesh","patel","nitesh@gmail.com");
			Student student2 = new Student("raj","patel","raj@gmail.com");
			
			course.addStudent(student1);
			course.addStudent(student2);
			
			
			session.save(student1);
			session.save(student2);
			System.out.println("saved student : " + course.getStudents());
			
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			
			session.close();
			
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
