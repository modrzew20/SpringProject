package com.example.springproject.repository.repositoryExceptions;

public class CantCreateCourierException extends AbstractException{
    public CantCreateCourierException() {
        super();
    }

    public CantCreateCourierException(String message) {
        super(message);
    }
}
