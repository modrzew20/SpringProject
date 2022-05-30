package com.example.springproject.repository.repositoryExceptions;

public class NoCourierForThisRegionException extends AbstractException{
    public NoCourierForThisRegionException() {
        super();
    }

    public NoCourierForThisRegionException(String message) {
        super(message);
    }
}
