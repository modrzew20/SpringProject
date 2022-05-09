package com.example.springproject.repository.repositoryExceptions;

public class ItemNotFound extends AbstractException{

    public ItemNotFound() {
        super();
    }

    public ItemNotFound(String message) {
        super(message);
    }
}
