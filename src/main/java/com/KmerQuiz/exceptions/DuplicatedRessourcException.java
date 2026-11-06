package com.KmerQuiz.exceptions;

public class DuplicatedRessourcException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicatedRessourcException(String message) {
        super(message);
    }

    public DuplicatedRessourcException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
