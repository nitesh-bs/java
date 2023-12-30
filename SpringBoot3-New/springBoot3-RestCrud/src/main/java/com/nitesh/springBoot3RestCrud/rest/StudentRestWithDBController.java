package com.nitesh.springBoot3RestCrud.rest;

import com.nitesh.springBoot3RestCrud.entity.Student;
import com.nitesh.springBoot3RestCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa")
public class StudentRestWithDBController {

    private StudentService studentService;

    @Autowired
    public StudentRestWithDBController(@Qualifier("studentServiceJpaImpl") StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public List<Student> findAllStudent(){
        return studentService.findAllStudent();
    }

    @GetMapping("/student/{studentId}")
    public Student findStudenyById(@PathVariable int studentId){
        return studentService.findStudentById(studentId);
    }

    @PostMapping("/student")
    public Student save(@RequestBody Student student){
        student.setStudId(0);
        return studentService.saveOrUpdate(student);
    }

    @PutMapping("/student")
    public Student update(@RequestBody Student student){
        return studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteByStudentId(@PathVariable int studentId){
        studentService.deleteStudentById(studentId);
    }


}
