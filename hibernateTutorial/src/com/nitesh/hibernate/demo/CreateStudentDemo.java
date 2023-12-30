package com.nitesh.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml") // if file not given it take
																							// default name
				.addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {

			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth;
			
				theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			

			Student student = new Student("Pauly", "Doe", "paul@luv.com", theDateOfBirth);
			session.beginTransaction();

			session.save(student);

			session.getTransaction().commit();

		}  catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}

	}

}
