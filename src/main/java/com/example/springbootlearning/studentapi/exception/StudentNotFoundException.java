package com.example.springbootlearning.studentapi.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student with " + id + " not found");
    }
}
