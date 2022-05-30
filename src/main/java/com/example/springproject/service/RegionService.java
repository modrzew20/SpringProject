package com.example.springproject.service;

import com.example.springproject.model.Region;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.repository.repositoryExceptions.RegionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class RegionService {

    @Autowired
    RegionRepo regionRepo;

    public ArrayList<Region> allRegion() {
        return regionRepo.all();
    }

    public boolean createRegion(String name, double N_limit,double S_limit,double W_limit,double E_limit) throws CantCreateRegionException, InvalidDataException {
        return regionRepo.create(new Region(UUID.randomUUID(),name,N_limit,S_limit,W_limit,E_limit));
    }

    public boolean deleteRegion(UUID regionUUID) throws RegionNotFoundException {
        return regionRepo.delete(regionUUID);
    }

    public Region findRegion(UUID regionUUID) throws RegionNotFoundException {
        return regionRepo.find(regionUUID);
    }

}
