package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;

public class EagerLazyDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take
																							// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {

			
			session.beginTransaction();

			int theId  = 1;
			
			Instructor instructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor : "+ instructor);
			
			System.out.println("Course : "+ instructor.getCourses());
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			
			session.close();
			
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
