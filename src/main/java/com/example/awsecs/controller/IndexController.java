//package com.example.awsecs.controller;
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.example.awsecs.service.StudentService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//@Slf4j
//public class IndexController {
//    @Autowired
//    private StudentService studentService;
//    @GetMapping("/index")
//    public String getIndex(Model model) {
//        log.info(studentService.toString());
//        model.addAttribute("message", "test data");
//        return "index";
//    }
////
//    @RequestMapping(value="/login", method= RequestMethod.GET)
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping(value = {"/", "/home"})
//    public String homepage() {
//        return "home"; // Trả về home.html
//    }
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello"; // Trả về hello.html
//    }
//}
