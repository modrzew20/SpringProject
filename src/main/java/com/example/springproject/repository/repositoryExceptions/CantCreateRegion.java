package com.example.springproject.repository.repositoryExceptions;

public class CantCreateRegion extends AbstractException{
    public CantCreateRegion() {
        super();
    }

    public CantCreateRegion(String message) {
        super(message);
    }
}
