package com.example.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestExceptionHandler {

    @ExceptionHandler(value={UserNotFoundException.class})
    public ResponseEntity<String> handlerUserNotFound(UserNotFoundException e){
        System.out.println("If UserNotFoundException throws, it will be intercepted by this handler");
        return new ResponseEntity(e.getMessage() + " Not Found", HttpStatus.OK);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<String> handlerException(Exception e){
        System.out.println("General Exception is going to be handled here");
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
