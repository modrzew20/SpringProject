package com.example.springproject.repository;

import com.example.springproject.model.Courier;
import com.example.springproject.repository.repositoryExceptions.CantCreateCourierException;
import com.example.springproject.repository.repositoryExceptions.CourierNotFoundException;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegionException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CourierRepo implements AbstractRepo<Courier>{

    ArrayList<Courier> allCourier;

    public CourierRepo() throws CantCreateCourierException {
        allCourier = new ArrayList<>();
        this.create(new Courier(UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c"),0,0,UUID.fromString("26699969-59b4-42f1-8c70-6d403a522767")));
        this.create(new Courier(UUID.fromString("1b12b987-b96a-4c17-9428-9376ff8e26e5"),16,0,UUID.fromString("4c6d7ecc-efc3-44ce-95e8-44182f86362b")));
        this.create(new Courier(UUID.fromString("b442336c-5ff2-4ef3-baa9-50b66820de43"),-9,0,UUID.fromString("f13f7383-1b75-439a-b44f-589f94f4a5e0")));
    }

    public ArrayList<Courier> all() {
        return allCourier;
    }

    public boolean create(Courier item) throws CantCreateCourierException {
        if (allCourier.stream().anyMatch(courier -> courier.getRegion().equals(item.getRegion())))
            throw new CantCreateCourierException("Courier for this region already exist");
        return allCourier.add(item);
    }

    public boolean delete(UUID uuid) throws CourierNotFoundException {
        boolean removed = allCourier.removeIf(x -> x.getUuid().equals(uuid));
        if (!removed) throw new CourierNotFoundException("Courier doesn't exist");
        return removed;
    }

    public Courier find(UUID uuid) throws CourierNotFoundException {
        return allCourier.stream().filter(c -> c.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()-> new CourierNotFoundException("Courier doesn't exist"));
    }

    public Courier getCourierForRegion (UUID region) throws NoCourierForThisRegionException {
        return allCourier.stream().filter(c -> c.getRegion().equals(region)).findFirst()
                .orElseThrow(() -> new NoCourierForThisRegionException("No courier for this region"));
    }
}
