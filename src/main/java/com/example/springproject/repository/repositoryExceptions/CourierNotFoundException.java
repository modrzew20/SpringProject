package com.example.springproject.repository.repositoryExceptions;

public class CourierNotFoundException extends AbstractException{
    public CourierNotFoundException() {
        super();
    }

    public CourierNotFoundException(String message) {
        super(message);
    }
}
