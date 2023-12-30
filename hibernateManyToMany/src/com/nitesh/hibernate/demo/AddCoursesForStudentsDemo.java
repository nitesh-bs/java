package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Review;
import com.nitesh.hibernate.demo.entity.Student;
import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;

public class AddCoursesForStudentsDemo {

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

			int studId = 2;
			
			Student student = session.get(Student.class, studId);

			System.out.println("raj loaded : "+student);
			System.out.println("raj course : "+student.getCourses());
			
			
			
			Course course = new Course("Android Development");
			Course course2 = new Course("Ios Development");
			
			
			course.addStudent(student);
			course2.addStudent(student);
			
			session.save(course);
			session.save(course2);
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			
			session.close();
			
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
