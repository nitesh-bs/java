package com.nitesh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nitesh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDaoHIbernateImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	
	@Autowired
	public EmployeeDaoHIbernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	public List<Employee> findAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Employee> query = session.createQuery("from Employee",Employee.class);
		
		List<Employee> employees = query.getResultList();
		
		return employees;
	}



	@Override
	public Employee findById(int theId) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, theId);
		
		return employee;
	}



	@Override
	public void save(Employee employee) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(employee);
		
	}



	@Override
	public void deleteById(int theId) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", theId);
		
		query.executeUpdate();
		
	}

}
