package com.example.springproject.repository.repositoryExceptions;

public class CantCreateCourier extends AbstractException{
    public CantCreateCourier() {
        super();
    }

    public CantCreateCourier(String message) {
        super(message);
    }
}
