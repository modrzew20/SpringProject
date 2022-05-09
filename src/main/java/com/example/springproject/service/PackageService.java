package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Package;
import com.example.springproject.model.Region;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.PackageRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.service.serviceExceptions.RegionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageService {

    @Autowired
    PackageRepo packageRepo;

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    CourierRepo courierRepo;

    public List<Package> all() {
        return packageRepo.getArrayList();
    }

    public boolean create(double x_coords, double y_coords) throws RegionNotFoundException, NoCourierForThisRegion {
        Region region = (assignPackageToRegion(x_coords,y_coords));
        Courier courier = courierRepo.getCourier(region);
        return packageRepo.createElement(new Package(null,x_coords,y_coords,courier));
    }

    private Region assignPackageToRegion(double x_coords, double y_coords) throws RegionNotFoundException {
        for (Region region : regionRepo.getArrayList()) {

            // TODO add -+ -- +- || (sign of coordinates)
            if (region.getN_limit() < y_coords
                    && region.getS_limit() < y_coords
                        && region.getE_limit() > x_coords
                            && region.getW_limit() > x_coords) return region;
        }
        throw new RegionNotFoundException("Region doesn't exist for this package");
    }


}
