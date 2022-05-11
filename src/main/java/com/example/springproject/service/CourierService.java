package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Region;
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

    public boolean createCourier(double startPointX, double startPointY, UUID regionUUID) throws  CantCreateCourier, RegionNotFound {
        Region region = regionRepo.find(regionUUID);
        return courierRepo.create(new Courier(null,startPointX,startPointY,region));
    }

    public boolean deleteCourier(UUID courierUUID) throws CourierNotFound {
        return courierRepo.delete(courierUUID);
    }

    public Courier findCourier(UUID courierUUID) throws CourierNotFound {
        return courierRepo.find(courierUUID);
    }

    public Courier getAssignCourierForRegion(UUID regionUUID) throws NoCourierForThisRegion, RegionNotFound {
        Region region = regionRepo.find(regionUUID);
        return courierRepo.getCourierForRegion(region);
    }





}
