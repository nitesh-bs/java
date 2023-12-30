package com.nitesh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Employee;
import com.nitesh.hibernate.demo.entity.Student;

public class Activity8Demo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			/*
			 * session.beginTransaction();
			 * 
			 * Employee employee = new Employee("Nitesh", "Shekhada", "Bigscal"); Employee
			 * employee2 = new Employee("Drashti", "Malvi", "LnT"); Employee employee3 = new
			 * Employee("Monika", "Agola", "Bigscal");
			 * 
			 * session.save(employee); session.save(employee2); session.save(employee3);
			 * 
			 * session.getTransaction().commit();
			 */

			/*
			 * session.beginTransaction();
			 * 
			 * Employee employee = new Employee("Raj", "Shah", "Abc");
			 * 
			 * session.save(employee);
			 * 
			 * session.getTransaction().commit();
			 * 
			 * 
			 * //read employee by primary key session = factory.getCurrentSession();
			 * session.beginTransaction();
			 * 
			 * Employee emp = session.get(Employee.class, employee.getId());
			 * 
			 * System.out.println(emp);
			 * 
			 * session.getTransaction().commit();
			 */

			/*
			 * // read employee by company session = factory.getCurrentSession();
			 * session.beginTransaction();
			 * 
			 * List<Employee> employees =
			 * session.createQuery("from Employee e where e.company='Bigscal'").list();
			 * 
			 * for (Employee e : employees) {
			 * 
			 * System.out.println(e); }
			 * 
			 * session.getTransaction().commit();
			 */

			//delete emp by primary key
			
			int empid = 1;

			session.beginTransaction();

			Employee empl = session.get(Employee.class, empid);
			
			session.delete(empl);

			session.getTransaction().commit();

		} finally {
			// TODO: handle finally clause
			factory.close();
		}

	}

}
