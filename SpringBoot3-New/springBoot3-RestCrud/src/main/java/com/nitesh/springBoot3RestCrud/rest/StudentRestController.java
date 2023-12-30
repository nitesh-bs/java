package com.nitesh.springBoot3RestCrud.rest;

import com.nitesh.springBoot3RestCrud.entity.Student;
import com.nitesh.springBoot3RestCrud.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private  List<Student> students;
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();
        students.add(new Student("Nitesh","Shekhada","n@gmail.com"));
        students.add(new Student("Raj","Patel","r@gmail.com"));
        students.add(new Student("Kishan","Shah","k@gmail.com"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

     @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if( (studentId >= students.size()) || studentId < 0){
            throw  new StudentNotFoundException("StudentNotFound : "+studentId);
         }
        return students.get(studentId);
    }
}
