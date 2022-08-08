package com.example.awsecs.service;

import java.util.ArrayList;
import java.util.List;

import com.example.awsecs.dto.StudentDTO;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public List<StudentDTO> getAllStudents (){
        var result = new ArrayList<StudentDTO>();
        result.add(randomStudent());
        result.add(randomStudent());
        result.add(randomStudent());
        result.add(randomStudent());
        return result;
    }

    private StudentDTO randomStudent (){
        return StudentDTO.builder()
                .id(1)
                .age(13)
                .gpa(4.5f)
                .name("Duong")
                .build();
    }

}
