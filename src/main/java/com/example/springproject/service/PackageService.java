package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Package;
import com.example.springproject.model.Region;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.PackageRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.repository.repositoryExceptions.PackageNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;


@Service
public class PackageService {

    @Autowired
    PackageRepo packageRepo;

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    CourierRepo courierRepo;

    public ArrayList<Package> allPackage() {
        return packageRepo.all();
    }

    public boolean createPackage(double x_coords, double y_coords) throws ItemNotFound, NoCourierForThisRegion{
        Region region = (regionRepo.findRegionForPackage(x_coords,y_coords));
        Courier courier = courierRepo.getCourierForRegion(region);
        return packageRepo.create(Package.builder().x_coordinate(x_coords)
                .y_coordinate(y_coords).courier(courier).build());

    }

    public boolean deletePackage(UUID packageUUID) throws PackageNotFound {
        return packageRepo.delete(packageUUID);
    }

    public Package findPackage(UUID packageUUID) throws PackageNotFound {
        return packageRepo.find(packageUUID);
    }

    public ArrayList<Package> getPackagesAssignToCourier(UUID courierUUID){
        return packageRepo.couriersPackage(courierUUID);
    }




}
