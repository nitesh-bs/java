package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml") // if file not given it take default name
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session= sessionFactory.getCurrentSession();
		
		try {
			
			Student student=new Student("John","Duo","john@gmail.com");
			
			session.beginTransaction();
			
			session.save(student);
			
			session.getTransaction().commit();
			
			//read student by primary key
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Student Id : "+student.getId());
			
			Student studentRead = session.get(Student.class, student.getId());
			
			System.out.println("Student read : "+studentRead);
			
			session.getTransaction().commit();
			
 			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}
		

	}

}
