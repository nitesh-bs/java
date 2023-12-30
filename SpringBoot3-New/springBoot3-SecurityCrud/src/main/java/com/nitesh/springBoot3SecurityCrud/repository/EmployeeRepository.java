package com.nitesh.springBoot3SecurityCrud.repository;

import com.nitesh.springBoot3SecurityCrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Default Spring Data Rest Page size =20
// http://localhost:8080/stud?sort=lastName,firstName,desc

//@RepositoryRestResource(path = "stud")

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}

