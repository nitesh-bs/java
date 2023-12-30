package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Instructor;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take
																							// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {

			Instructor intructor = new Instructor("yash","patel","yash@gmail.com");
			
			InstructorDetail instructorDetail= new InstructorDetail("http://www.youtubes.com","Cricket");
			
			intructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();

			session.save(intructor);
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
