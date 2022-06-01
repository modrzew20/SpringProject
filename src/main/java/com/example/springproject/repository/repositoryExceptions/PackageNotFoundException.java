package com.example.springproject.repository.repositoryExceptions;

public class PackageNotFoundException extends AbstractException{
    public PackageNotFoundException() {
        super();
    }

    public PackageNotFoundException(String message) {
        super(message);
    }
}
