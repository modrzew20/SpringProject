package com.example.springproject;

import com.example.springproject.model.Region;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegion;
import com.example.springproject.repository.repositoryExceptions.RegionNotFound;
import com.example.springproject.service.RegionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegionServiceTest {

    private UUID findRegionUUID;
    private Region region;

    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private RegionService regionService;

    @BeforeAll
    void setUpAll() throws CantCreateRegion {
        findRegionUUID = UUID.randomUUID();
        region = new Region(findRegionUUID, "region_0", 40.0,
                39.0, 50.3, 60.4);
        regionRepo.create(region);
    }

    @Order(1)
    @Test
    public void findRegionTest() throws RegionNotFound {
        assertEquals(region.getName(), regionService.findRegion(findRegionUUID).getName());
        assertThrows(RegionNotFound.class, () -> {
            regionService.findRegion(null);
        });
    }


    @Order(2)
    @Test
    public void createRegionTest() {
        assertDoesNotThrow(() -> {
            regionService.createRegion("name", 30.0, 50.1, 43.2, 32.1);
        });
    }

    @Order(3)
    @Test
    public void deleteRegionTest() throws RegionNotFound {
        assertTrue(regionService.deleteRegion(findRegionUUID));
        assertThrows(RegionNotFound.class, () -> {
            regionService.deleteRegion(findRegionUUID);
        });
    }

}
