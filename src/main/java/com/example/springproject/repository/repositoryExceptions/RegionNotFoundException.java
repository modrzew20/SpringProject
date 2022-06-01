package com.example.springproject.repository.repositoryExceptions;

public class RegionNotFoundException extends AbstractException{
    public RegionNotFoundException() {
    }

    public RegionNotFoundException(String message) {
        super(message);
    }
}
