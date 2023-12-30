package com.nitesh.springBoot3RestCrud.repository;

import com.nitesh.springBoot3RestCrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Default Spring Data Rest Page size =20
// http://localhost:8080/stud?sort=lastName,firstName,desc

//@RepositoryRestResource(path = "stud")


public interface StudentRepository extends JpaRepository<Student,Integer> {
}
