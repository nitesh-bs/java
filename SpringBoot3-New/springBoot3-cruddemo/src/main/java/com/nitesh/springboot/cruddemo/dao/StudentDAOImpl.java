package com.nitesh.springboot.cruddemo.dao;

import com.nitesh.springboot.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional  // For updation in database
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAllStudents() {
        TypedQuery<Student> typedQuery = entityManager.createQuery("from Student",Student.class);
//      TypedQuery<Student> typedQuerys = entityManager.createQuery("from Student WHERE lastName=:theData",Student.class);
//      typedQuerys.setParameter("theData",typedQuerys);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int studentId) {
        Student student = entityManager.find(Student.class,studentId);
        entityManager.remove(student);
    }
}
