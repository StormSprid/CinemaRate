package com.example.cinemarate.Exception;

import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * {@code @ControllerAdvice} is a Spring annotation that allows us to define
 * centralized exception handling across all controllers in the application.
 * It tells Spring to process all exceptions using this handler class
 */

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<ErrorModel>> handleCustomException(CustomException e){
        System.out.println("Custom exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(e.getErrors(), HttpStatus.BAD_REQUEST);

    }}
