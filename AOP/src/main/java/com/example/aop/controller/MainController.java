package com.example.aop.controller;

import com.example.aop.domain.UserResponse;
import com.example.aop.exception.UserNotFoundException;
import com.example.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserResponse> findAll(){
        UserResponse userResponse = UserResponse.builder().userList(userService.findAll()).build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{name}")
    public ResponseEntity findByName(@PathVariable String name) throws UserNotFoundException {
        UserResponse userResponse = UserResponse.builder().userList(userService.findByName(name)).build();
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
