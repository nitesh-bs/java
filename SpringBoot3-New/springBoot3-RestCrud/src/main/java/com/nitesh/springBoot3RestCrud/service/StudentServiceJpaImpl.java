package com.nitesh.springBoot3RestCrud.service;

import com.nitesh.springBoot3RestCrud.entity.Student;
import com.nitesh.springBoot3RestCrud.exception.StudentNotFoundException;
import com.nitesh.springBoot3RestCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceJpaImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceJpaImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(int studentId) {
        Optional<Student> byId = studentRepository.findById(studentId);
        if(!byId.isPresent()){
            throw new StudentNotFoundException("Student Not Found!!");
        }
        return byId.get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }
}
