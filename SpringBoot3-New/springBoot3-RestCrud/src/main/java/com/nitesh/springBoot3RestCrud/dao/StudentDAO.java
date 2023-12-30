package com.nitesh.springBoot3RestCrud.dao;

import com.nitesh.springBoot3RestCrud.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAllStudents();

    Student findById(int studentId);

    Student save(Student student);

    void deleteById(int studentId);
}
