package com.example.awsecs.service;

import java.util.List;

import com.example.awsecs.dto.StudentDTO;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Integer studentId);
    List<StudentDTO> getStudentsByName(String name);

    StudentDTO createStudent(StudentDTO studentDTO);
}
