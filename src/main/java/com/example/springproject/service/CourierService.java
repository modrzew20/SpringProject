package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CourierService {

    @Autowired
    CourierRepo courierRepo;

    @Autowired
    RegionRepo regionRepo;

    public ArrayList<Courier> allCourier () {
        return courierRepo.all();
    }

    public boolean createCourier(double startPointX, double startPointY, UUID regionUUID) throws CantCreateCourierException, RegionNotFoundException {
        return courierRepo.create(new Courier(UUID.randomUUID(),startPointX,startPointY,regionUUID));
    }

    public boolean deleteCourier(UUID courierUUID) throws CourierNotFoundException {
        return courierRepo.delete(courierUUID);
    }

    public Courier findCourier(UUID courierUUID) throws CourierNotFoundException {
        return courierRepo.find(courierUUID);
    }

    public Courier getAssignCourierForRegion(UUID regionUUID) throws NoCourierForThisRegionException, RegionNotFoundException {
        return courierRepo.getCourierForRegion(regionUUID);
    }





}
