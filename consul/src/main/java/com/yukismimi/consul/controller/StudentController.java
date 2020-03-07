package com.yukismimi.consul.controller;

import com.yukismimi.consul.entity.Student;
import com.yukismimi.consul.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private StudentRepository repository;

    @GetMapping("/student/all")
    public List<Student> getAll(){
        return repository.findAll();
    }

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }
}
