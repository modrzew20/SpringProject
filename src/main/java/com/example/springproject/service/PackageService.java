package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Package;
import com.example.springproject.model.Region;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.PackageRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.ItemNotFoundException;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegionException;
import com.example.springproject.repository.repositoryExceptions.PackageNotFoundException;
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

    public boolean createPackage(double x_coords, double y_coords) throws ItemNotFoundException, NoCourierForThisRegionException {
        Region region = (regionRepo.findRegionForPackage(x_coords,y_coords));
        Courier courier = courierRepo.getCourierForRegion(region.getUuid());
        return packageRepo.create(Package.builder().uuid(UUID.randomUUID()).x_coordinate(x_coords)
                .y_coordinate(y_coords).courier(courier.getUuid()).build());

    }

    public boolean deletePackage(UUID packageUUID) throws PackageNotFoundException {
        return packageRepo.delete(packageUUID);
    }

    public Package findPackage(UUID packageUUID) throws PackageNotFoundException {
        return packageRepo.find(packageUUID);
    }

    public ArrayList<Package> getPackagesAssignToCourier(UUID courierUUID){
        return packageRepo.couriersPackage(courierUUID);
    }




}
