package com.example.springproject.repository;

import com.example.springproject.repository.repositoryExceptions.*;

import java.util.ArrayList;
import java.util.UUID;

public interface AbstractRepo<T> {

    ArrayList<T> all();
    boolean create(T item) throws CantCreateCourierException, CantCreateRegionException, InvalidDataException;
    boolean delete(UUID uuid) throws CourierNotFoundException, PackageNotFoundException, RegionNotFoundException;
    T find(UUID uuid) throws CourierNotFoundException, PackageNotFoundException, RegionNotFoundException;
}
