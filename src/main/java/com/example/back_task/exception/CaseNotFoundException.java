package com.example.back_task.exception;

public class CaseNotFoundException extends RuntimeException {
    public CaseNotFoundException(String message) {
        super(message);
    }
}
