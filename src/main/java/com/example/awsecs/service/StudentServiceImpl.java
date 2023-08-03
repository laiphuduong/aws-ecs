package com.example.awsecs.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.awsecs.common.ExceptionMessage;
import com.example.awsecs.dto.StudentDTO;
import com.example.awsecs.entity.Student;
import com.example.awsecs.repository.StudentRepository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope(value = "prototype")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<StudentDTO> getAllStudents() {
        var result = new ArrayList<StudentDTO>();
        result.add(randomStudent());
        result.add(randomStudent());
        result.add(randomStudent());
        result.add(randomStudent());

        return result;
    }

    public StudentDTO getStudentById(Integer studentId) {
        try {
            var result = studentRepository.getStudentById(studentId);
            if (result.isPresent()) {
                return toStudentDTO(result.get());
            }
        } catch (Exception e) {
            log.error(ExceptionMessage.NOT_FOUND_IN_DB);
        }
        return null;
    }

    public List<StudentDTO> getStudentsByName(String name) {
        var lstStudent = studentRepository.findAllByNameContains(name);
        return lstStudent.stream().map(this::toStudentDTO).collect(Collectors.toList());
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        var student = new Student();
        student.setAge(studentDTO.getAge());
        student.setName(studentDTO.getName());
        student.setGpa(studentDTO.getGpa());
        studentRepository.save(student);
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    private StudentDTO toStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .gpa(student.getGpa())
                .age(student.getAge())
                .build();
    }

    private StudentDTO randomStudent() {
        return StudentDTO.builder()
                .id(1)
                .age(13)
                .gpa(4.5f)
                .name("Duong")
                .build();
    }

}
