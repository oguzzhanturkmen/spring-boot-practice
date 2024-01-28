package com.spb.springbootpractice.exception;

public class EmailConflictException extends RuntimeException {
    public EmailConflictException(String emailAlreadyInUse) {
        super(emailAlreadyInUse);
    }
}
