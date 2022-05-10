package com.example.springproject.repository.repositoryExceptions;

public class CourierNotFound extends AbstractException{
    public CourierNotFound() {
        super();
    }

    public CourierNotFound(String message) {
        super(message);
    }
}
