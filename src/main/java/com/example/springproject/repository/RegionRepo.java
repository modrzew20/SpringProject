package com.example.springproject.repository;

import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class RegionRepo implements AbstractRepo<Region> {
    ArrayList<Region> allRegion;

    @Override
    public ArrayList<Region> getArrayList() {
        return allRegion;
    }

    @Override
    public boolean createElement(Region element) {
        return allRegion.add(element);
    }

    @Override
    public boolean deleteElement(UUID uuid) throws ItemNotFound {
        boolean removed = allRegion.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new ItemNotFound("Item didn't found");
        return removed;
    }
}
