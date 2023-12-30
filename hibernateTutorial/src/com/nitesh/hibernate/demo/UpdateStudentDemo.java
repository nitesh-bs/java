package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml") // if file not given it take default name
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session= sessionFactory.getCurrentSession();
		
		try {
			
			int studentid = 1;
			
			session.beginTransaction();
			
			Student student=session.get(Student.class, studentid);

			student.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			//update all student email
			session= sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
 			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}
		

	}

}
