package com.example.springproject.service.serviceExceptions;

public class RegionNotFoundException extends Exception {

    public RegionNotFoundException() {
    }

    public RegionNotFoundException(String message) {
        super(message);
    }
}
