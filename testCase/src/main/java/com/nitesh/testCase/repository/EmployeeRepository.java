package com.nitesh.testCase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nitesh.testCase.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	 @Query("" +
	            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
	            "TRUE ELSE FALSE END " +
	            "FROM Employee s " +
	            "WHERE s.email = ?1"
	    )
	    Boolean selectExistsEmail(String email);
}
