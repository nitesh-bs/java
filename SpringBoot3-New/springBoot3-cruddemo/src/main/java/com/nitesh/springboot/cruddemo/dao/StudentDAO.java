package com.nitesh.springboot.cruddemo.dao;

import com.nitesh.springboot.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    Student findById(Integer id);

    List<Student> findAllStudents();

    void update(Student student);

    void delete(int student);
}