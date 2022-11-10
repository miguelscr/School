package com.tcs.School.controller;

import com.tcs.School.entity.Item;
import com.tcs.School.model.StudentModel;
import com.tcs.School.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<StudentModel> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/student/{id}")
    public Optional<StudentModel> getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @PostMapping("/student")
    public StudentModel insertStudent(@RequestBody StudentModel student){
        return studentService.saveStudent(student);
    }

    @PutMapping("/student/{id}")
    public StudentModel updateStudent(@RequestBody StudentModel student, @PathVariable Long id){
        return studentService.updateStudent(student,id);
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/studentWithCourse/{id}")
    public Item getStudentsAndCourses(@PathVariable Long id){
        return studentService.getStudentsAndCourses(id);
    }
}