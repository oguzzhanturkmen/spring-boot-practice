package com.spb.springbootpractice.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String studentNotFound) {
        super(studentNotFound);
    }
}
