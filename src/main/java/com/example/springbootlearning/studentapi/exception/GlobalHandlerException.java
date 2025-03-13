package com.example.springbootlearning.studentapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStudentException(StudentNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("message", ex.getMessage());
        errorBody.put("timestamp", LocalDate.now().toString());
        errorBody.put("error","Not Found");
        errorBody.put("message","Student Not Found");

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);


    }
}
