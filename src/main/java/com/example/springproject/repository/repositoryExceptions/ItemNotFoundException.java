package com.example.springproject.repository.repositoryExceptions;

public class ItemNotFoundException extends AbstractException{

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
