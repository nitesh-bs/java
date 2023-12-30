package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take																	// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
	
			session.beginTransaction();

			int insId = 1;
			
			Instructor instructor = session.get(Instructor.class, insId);
			
			if(instructor != null) {
				session.delete(instructor);
			}
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
