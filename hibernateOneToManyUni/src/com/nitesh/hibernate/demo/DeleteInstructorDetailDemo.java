package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Instructor;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take																	// default name
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
	
			session.beginTransaction();

			int insDetailId = 3;
			
		
			//get Instructor Detail
			InstructorDetail detail =
					session.get(InstructorDetail.class,	insDetailId);
			
			System.out.println(" details : "+detail);
			
			
			System.out.println("associated instructor : "+detail.getInstructor());
			
			//remove associated reference.
			//break bi-directional link			
			detail.getInstructor().setInstructorDetail(null); // only used when only delete into one table in forign key
			
			
			
			//not delete instructor detail
			session.delete(detail);
			
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
