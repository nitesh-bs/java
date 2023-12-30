package com.nitesh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDaoJPAImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoJPAImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		Query query = entityManager.createQuery("from Employee");
		
		List<Employee> employees = query.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		Employee employee = entityManager.find(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		Employee dbEmployee= entityManager.merge(employee);
		
		employee.setId(dbEmployee.getId());
		
		
		
	}

	@Override
	public void deleteById(int theId) {
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", theId);
		
		query.executeUpdate();
	}

}
