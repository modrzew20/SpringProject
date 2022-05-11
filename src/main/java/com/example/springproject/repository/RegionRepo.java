package com.example.springproject.repository;

import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegion;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.RegionNotFound;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class RegionRepo implements AbstractRepo<Region>{
    ArrayList<Region> allRegion;


    public ArrayList<Region> all() {
        return allRegion;
    }

    public boolean create(Region item) throws CantCreateRegion {
        if(isRegionReserved(item)) return allRegion.add(item);
        throw new CantCreateRegion("Region with the given data already exist");
    }

    public boolean delete(UUID uuid) throws RegionNotFound {
        boolean removed = allRegion.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new RegionNotFound("Region doesn't exist");
        return removed;
    }

    public Region find(UUID uuid) throws RegionNotFound {
        return allRegion.stream().filter(r -> r.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()->new RegionNotFound("Region doesn't exist"));
    }

    public boolean isRegionReserved(Region item) {
        // TODO check if region consist to another
        for (Region region : allRegion) {
            if(item.getN_limit() == region.getN_limit()) return false;
        }
        return true;
    }

    public Region findRegionForPackage(double x_coords, double y_coords) throws ItemNotFound {
        for (Region region : allRegion) {

            // TODO add -+ -- +- || (sign of coordinates)
            if (region.getN_limit() < y_coords
                    && region.getS_limit() < y_coords
                    && region.getE_limit() > x_coords
                    && region.getW_limit() > x_coords) return region;
        }
        throw new ItemNotFound("No region for this package");
    }
}
