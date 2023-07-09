package com.example.awsecs.controller;

import javax.validation.Valid;

import com.example.awsecs.dto.StudentDTO;
import com.example.awsecs.service.StudentGroupService;
import com.example.awsecs.service.StudentService;
import com.example.awsecs.service.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentGroupService studentGroupService;



    @GetMapping("/health")
    public ResponseEntity<Object> test(@RequestParam(name = "studentDTO") StudentDTO studentDTO) {
        return new ResponseEntity<>("Hello word", HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getAllStudents() {
        var result = studentService.getAllStudents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/student-group")
    public ResponseEntity<Object> getStudentGroup() {
        var result = studentGroupService.getData();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById (@PathVariable("id") Integer studentId){
        var result = studentService.getStudentById(studentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/student")
    public ResponseEntity<Object> getStudentsByName (@RequestParam("name") String name){
        var result = studentService.getStudentsByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/student")
    public ResponseEntity<Object> createStudent (@RequestBody @Valid StudentDTO studentDTO){
        var result = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
