package com.example.springproject.repository.repositoryExceptions;

public class CantCreateRegionException extends AbstractException{
    public CantCreateRegionException() {
        super();
    }

    public CantCreateRegionException(String message) {
        super(message);
    }
}
