package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Review;
import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take
																							// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {

			
			session.beginTransaction();

			Course course= new Course("Spring Boot");
			
			course.addReview(new Review("Greate Course!!"));
			course.addReview(new Review("Best Course!!"));
			course.addReview(new Review("Good Course!!"));
			
			session.save(course);
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			
			session.close();
			
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
