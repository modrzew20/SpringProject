package com.example.springproject.repository.repositoryExceptions;

public abstract class AbstractException extends Exception{
    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }
}
