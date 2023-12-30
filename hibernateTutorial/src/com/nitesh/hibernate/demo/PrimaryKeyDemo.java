package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml") // if file not given it take default name
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session= sessionFactory.getCurrentSession();
		
		try {
			
			Student student2=new Student("yash","shekhada","yash@gmail.com");
			Student student3=new Student("raj","patel","raj@gmail.com");
			Student student4=new Student("kishan","shah","kishan@gmail.com");
			
			
			
			session.beginTransaction();
			
			session.save(student2);
			session.save(student3);
			session.save(student4);
			
			session.getTransaction().commit();
 			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}
		

	}

}
