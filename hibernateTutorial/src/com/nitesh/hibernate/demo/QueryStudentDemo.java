package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;import antlr.collections.List;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml") // if file not given it take default name
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session= sessionFactory.getCurrentSession();
		
		try {
				
			session.beginTransaction();
			
			java.util.List<Student> students = session.createQuery("from Student").list();

			
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.lastName='Shekhada'").list();
			
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.lastName='Shekhada' OR s.firstName='nitesh'").list();
			
			displayStudents(students);
			
			students = session.createQuery("from Student s where s.email LIKE '%sh@gmail.com'").list();
			
			displayStudents(students);
			
			session.getTransaction().commit();
 			
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}
		

	}

	private static void displayStudents(java.util.List<Student> students) {
		for(Student tempStud:students) {
			System.out.println(tempStud);
		}
	}

}
