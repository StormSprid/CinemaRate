package com.example.cinemarate.Exception;

import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code @ControllerAdvice} is a Spring annotation that allows us to define
 * centralized exception handling across all controllers in the application.
 * It tells Spring to process all exceptions using this handler class
 */

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException e){


        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel  error = null;
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrorList){
            error = new ErrorModel();
            error.setCode(fe.getCode());
            error.setMessage(fe.getDefaultMessage());
            errorModelList.add(error);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<ErrorModel>> handleCustomException(CustomException e){
        System.out.println("Custom exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(e.getErrors(), HttpStatus.BAD_REQUEST);

    }}
