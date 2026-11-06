package com.KmerQuiz.exceptions;

public class RessourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RessourceNotFoundException(String message) {
        super(message);
    }

    public RessourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
