package com.example.awsecs.controller;

import lombok.extern.slf4j.Slf4j;

import com.example.awsecs.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/")
    public String getIndex(Model model) {
        log.info(studentService.toString());
        model.addAttribute("message", "test data");
        return "index";
    }
}
