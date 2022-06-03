package com.example.springproject.repository;

import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.repository.repositoryExceptions.ItemNotFoundException;
import com.example.springproject.repository.repositoryExceptions.RegionNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class RegionRepo implements AbstractRepo<Region>{

    ArrayList<Region> allRegion;

    public RegionRepo() throws CantCreateRegionException, InvalidDataException {
        allRegion = new ArrayList<>();
        this.create(new Region(UUID.fromString("26699969-59b4-42f1-8c70-6d403a522767"),"Region1",2,1,1,2));
        this.create(new Region(UUID.fromString("4c6d7ecc-efc3-44ce-95e8-44182f86362b"),"Region2",2,1,-3,-2));
        this.create(new Region(UUID.fromString("f13f7383-1b75-439a-b44f-589f94f4a5e0"),"Region3",-1,-2,-3,-2));
        this.create(new Region(UUID.fromString("cb9f9d71-782f-4099-9742-398b9584b0d5"),"Region4",-1,-2,1,2));
        this.create(new Region(UUID.fromString("bb99088a-1faf-4918-8a3c-3c863be4a984"),"Region5",0.5,-0.5,-0.5,0.5));
        this.create(new Region(UUID.fromString("ab990ea0-eae0-437f-98f8-40b216f932c2"),"Region6",0.5,-0.5,-3,-2));
        this.create(new Region(UUID.fromString("df1941d5-20b3-48ae-9186-cd37b47aac37"),"Region7",0.5,-0.5,1,2));
        this.create(new Region(UUID.fromString("c42f4dc3-5178-43f0-b9b9-1e42b094bd23"),"Region8",2,1,-0.5,0.5));
        this.create(new Region(UUID.fromString("b6d7bb81-732a-490e-bdf3-d3993bfe882b"),"Region9",-1,-2,-0.5,0.5));
        this.create(new Region(UUID.fromString("26699969-efc3-4099-9742-3c863be4a984"), "Lodz", 51.81492932246167, 51.7292002066985, 19.403668600445517, 19.510527631783667));
    }


    public ArrayList<Region> all() {
        return allRegion;
    }

    public boolean create(Region item) throws CantCreateRegionException, InvalidDataException {
        double N = item.getN_limit() + 180;
        double E = item.getE_limit() + 180;
        double S = item.getS_limit() + 180;
        double W = item.getW_limit() + 180;
        if(N < S || E < W) {
            throw new InvalidDataException("Invalid input data");
        }

        if(isRegionReserved(item)) return allRegion.add(item);
        throw new CantCreateRegionException("Region with the given data already exist");
    }

    public boolean delete(UUID uuid) throws RegionNotFoundException {
        boolean removed = allRegion.removeIf(x -> x.getUuid().equals(uuid));
        if (!removed) throw new RegionNotFoundException("Region doesn't exist");
        return removed;
    }

    public Region find(UUID uuid) throws RegionNotFoundException {
        return allRegion.stream().filter(r -> r.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()->new RegionNotFoundException("Region doesn't exist"));
    }

    public boolean isRegionReserved(Region item) {
        // TODO check if region belong to another
        return true;
    }

    public Region findRegionForPackage(double x_coords, double y_coords) throws ItemNotFoundException {
        double N, E, S, W, X, Y;
        for (Region region : allRegion) {
            N = region.getN_limit() + 180;
            E = region.getE_limit() + 180;
            S = region.getS_limit() + 180;
            W = region.getW_limit() + 180;
            X = x_coords + 180;
            Y = y_coords + 180;
            if(X >= W && X <= E && Y >= S && Y <= N) return region;
        }
        throw new ItemNotFoundException("No region for this package");
    }
}
