
package com.nitesh.springBootTestCase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nitesh.springBootTestCase.entity.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Person s WHERE s.personId = ?1")
	Boolean isPersonExitsById(Integer id);
}