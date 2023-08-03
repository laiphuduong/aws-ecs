package com.example.awsecs.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.example.awsecs.dto.StudentGroupDTO;
import com.example.awsecs.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StudentGroupService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentGroupDTO> getData () {
        log.info("starting function");
        var result = studentRepository.getStudentAndGroupNative();
        log.info("After that log, AOP will be run");
        return studentRepository.getStudentAndGroup();
    }
}
