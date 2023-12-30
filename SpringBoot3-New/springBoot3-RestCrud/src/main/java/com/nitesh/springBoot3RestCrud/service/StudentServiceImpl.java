package com.nitesh.springBoot3RestCrud.service;

import com.nitesh.springBoot3RestCrud.dao.StudentDAO;
import com.nitesh.springBoot3RestCrud.entity.Student;
import com.nitesh.springBoot3RestCrud.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDAO.findAllStudents();
    }

    @Override
    public Student findStudentById(int studentId) {
        Student student = studentDAO.findById(studentId);
        if(student == null){
            throw new StudentNotFoundException("Student Not Found "+studentId);
        }
        return student;
    }

    @Transactional
    @Override
    public Student saveOrUpdate(Student student) {
        return studentDAO.save(student);
    }

    @Transactional
    @Override
    public void deleteStudentById(int studentId) {
        studentDAO.deleteById(studentId);
    }
}
