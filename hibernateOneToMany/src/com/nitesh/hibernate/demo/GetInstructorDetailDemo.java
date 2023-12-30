package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Instructor;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take																	// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
	
			session.beginTransaction();

			int insDetailId = 254262;
			
		
			//get Instructor Detail
			InstructorDetail detail =
					session.get(InstructorDetail.class,	insDetailId);
			
			System.out.println(" details : "+detail);
			
			
			System.out.println("associated instructor : "+detail.getInstructor());
			
			
			
			session.getTransaction().commit();

			System.out.println("Done!");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}

	}

}
