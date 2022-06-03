package com.example.springproject.algorithm;

import com.example.springproject.model.Region;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.repository.repositoryExceptions.ItemNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgorithmTest {

    // CREATED REGIONS
//        this.create(new Region(UUID.fromString("26699969-59b4-42f1-8c70-6d403a522767"),"Region1",2,1,1,2));
//        this.create(new Region(UUID.fromString("4c6d7ecc-efc3-44ce-95e8-44182f86362b"),"Region2",2,1,-3,-2));
//        this.create(new Region(UUID.fromString("f13f7383-1b75-439a-b44f-589f94f4a5e0"),"Region3",-1,-2,-3,-2));
//        this.create(new Region(UUID.fromString("cb9f9d71-782f-4099-9742-398b9584b0d5"),"Region4",-1,-2,1,2));
//        this.create(new Region(UUID.fromString("bb99088a-1faf-4918-8a3c-3c863be4a984"),"Region5",0.5,-0.5,-0.5,0.5));
//        this.create(new Region(UUID.fromString("ab990ea0-eae0-437f-98f8-40b216f932c2"),"Region6",0.5,-0.5,-3,-2));
//        this.create(new Region(UUID.fromString("df1941d5-20b3-48ae-9186-cd37b47aac37"),"Region7",0.5,-0.5,1,2));
//        this.create(new Region(UUID.fromString("c42f4dc3-5178-43f0-b9b9-1e42b094bd23"),"Region8",2,1,-0.5,0.5));
//        this.create(new Region(UUID.fromString("b6d7bb81-732a-490e-bdf3-d3993bfe882b"),"Region9",-1,-2,-0.5,0.5));

    RegionRepo regionRepo;

    @BeforeEach
    public void init() throws CantCreateRegionException, InvalidDataException {
        regionRepo = new RegionRepo();
    }

    @Test
    void sizeTest() {
        assertEquals(9, regionRepo.all().size());
    }

    @Test
    void assignPackageToRegionTest() throws ItemNotFoundException {
        assertEquals(regionRepo.findRegionForPackage(1.5,1.5).getName(),"Region1");
        assertEquals(regionRepo.findRegionForPackage(-2.5,1.5).getName(),"Region2");
        assertEquals(regionRepo.findRegionForPackage(-2.5,-1.5).getName(),"Region3");
        assertEquals(regionRepo.findRegionForPackage(1.5,-1.5).getName(),"Region4");
        assertEquals(regionRepo.findRegionForPackage(0,0).getName(),"Region5");
        assertEquals(regionRepo.findRegionForPackage(-2.5,0).getName(),"Region6");
        assertEquals(regionRepo.findRegionForPackage(1.5,0).getName(),"Region7");
        assertEquals(regionRepo.findRegionForPackage(0,1.5).getName(),"Region8");
        assertEquals(regionRepo.findRegionForPackage(0,-1.5).getName(),"Region9");
        assertThrows(ItemNotFoundException.class, () -> regionRepo.findRegionForPackage(10,10));
    }

}
