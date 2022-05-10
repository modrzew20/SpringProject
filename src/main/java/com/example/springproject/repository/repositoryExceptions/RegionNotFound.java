package com.example.springproject.repository.repositoryExceptions;

public class RegionNotFound extends AbstractException{
    public RegionNotFound() {
    }

    public RegionNotFound(String message) {
        super(message);
    }
}
