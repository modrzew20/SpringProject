package com.example.springproject.repository.repositoryExceptions;

public class NoCourierForThisRegion extends AbstractException{
    public NoCourierForThisRegion() {
        super();
    }

    public NoCourierForThisRegion(String message) {
        super(message);
    }
}
