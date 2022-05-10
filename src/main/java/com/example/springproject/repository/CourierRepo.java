package com.example.springproject.repository;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.CantCreateCourier;
import com.example.springproject.repository.repositoryExceptions.CourierNotFound;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CourierRepo {

    ArrayList<Courier> allCourier;

    public ArrayList<Courier> all() {
        return allCourier;
    }

    public boolean create(Courier item) throws CantCreateCourier {
        if (allCourier.stream().anyMatch(courier -> courier.getRegion().equals(item.getRegion())))
            throw new CantCreateCourier("Courier for this region already exist");
        return allCourier.add(item);
    }

    public boolean delete(UUID uuid) throws CourierNotFound {
        boolean removed = allCourier.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new CourierNotFound("Courier doesn't exist");
        return removed;
    }

    public Courier find(UUID uuid) throws CourierNotFound {
        return allCourier.stream().filter(courier -> courier.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()-> new CourierNotFound("Courier doesn't exist"));
    }

    public Courier getCourierForRegion (Region region) throws NoCourierForThisRegion {
        return allCourier.stream().filter(c -> c.getRegion() == region).findFirst()
                .orElseThrow(() -> new NoCourierForThisRegion("No courier for this region"));
    }
}
