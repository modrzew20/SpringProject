package com.example.springproject.repository;

import com.example.springproject.model.Courier;
import com.example.springproject.repository.repositoryExceptions.*;

import java.util.ArrayList;
import java.util.UUID;

public interface AbstractRepo<T> {

    ArrayList<T> all();
    boolean create(T item) throws CantCreateCourier, CantCreateRegion;
    boolean delete(UUID uuid) throws CourierNotFound, PackageNotFound, RegionNotFound;
    T find(UUID uuid) throws CourierNotFound, PackageNotFound, RegionNotFound;
}
