package com.nitesh.springBoot3RestCrud.service;

import com.nitesh.springBoot3RestCrud.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudent();

    Student findStudentById(int studentId);

    Student saveOrUpdate(Student student);

    void deleteStudentById(int studentId);
}
