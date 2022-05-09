package com.example.springproject.repository;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CourierRepo implements AbstractRepo<Courier> {

    ArrayList<Courier> allCourier;

    @Override
    public ArrayList<Courier> getArrayList() {
        return allCourier;
    }

    @Override
    public boolean createElement(Courier element) {
        return allCourier.add(element);
    }

    @Override
    public boolean deleteElement(UUID uuid) throws ItemNotFound {
        boolean removed = allCourier.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new ItemNotFound("Item didn't found");
        return removed;
    }

    public Courier getCourier(Region region) throws NoCourierForThisRegion {
        return allCourier.stream().filter(c -> c.getRegion() == region).findFirst().orElseThrow(() -> new NoCourierForThisRegion("Region not exist"));
    }
}
