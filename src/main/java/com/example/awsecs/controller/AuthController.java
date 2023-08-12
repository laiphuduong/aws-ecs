package com.example.awsecs.controller;

import com.example.awsecs.dto.LoginDTO;
import com.example.awsecs.dto.UserDTO;
import com.example.awsecs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO){
        var result = userService.createUser(userDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody UserDTO userDTO){
        var result = userService.login(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginDTO> refresh(@RequestHeader(value = "refresh-token") String refreshToken){
        var result = userService.refresh(refreshToken);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
