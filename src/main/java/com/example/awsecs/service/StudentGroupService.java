package com.example.awsecs.service;

import java.util.List;

import com.example.awsecs.dto.StudentGroupDTO;
import com.example.awsecs.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentGroupDTO> getData () {
        var result = studentRepository.getStudentAndGroupNative();
        return studentRepository.getStudentAndGroup();
    }
}
