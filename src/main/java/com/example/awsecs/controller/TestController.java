package com.example.awsecs.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.example.awsecs.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;



    @GetMapping("/health")
    public ResponseEntity<Object> test(){
        return new ResponseEntity<>("Hello word", HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getAllStudents () {
        var result = studentService.getAllStudents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
