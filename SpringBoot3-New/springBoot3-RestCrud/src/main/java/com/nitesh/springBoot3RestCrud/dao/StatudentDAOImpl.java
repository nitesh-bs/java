package com.nitesh.springBoot3RestCrud.dao;

import com.nitesh.springBoot3RestCrud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StatudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAllStudents() {
        TypedQuery<Student> typedQuery = entityManager.createQuery("from Student",Student.class);
        List<Student> students = typedQuery.getResultList();
        return students;
    }

    @Override
    public Student findById(int studentId) {

        Student student = entityManager.find(Student.class,studentId);
        return student;
    }

    @Override
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public void deleteById(int studentId) {
        Student student = entityManager.find(Student.class,studentId);
        entityManager.remove(student);
    }
}
