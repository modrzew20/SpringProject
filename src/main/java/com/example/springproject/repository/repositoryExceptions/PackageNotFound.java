package com.example.springproject.repository.repositoryExceptions;

public class PackageNotFound extends AbstractException{
    public PackageNotFound() {
        super();
    }

    public PackageNotFound(String message) {
        super(message);
    }
}
