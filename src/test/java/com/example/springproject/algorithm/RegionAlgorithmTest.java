package com.example.springproject.algorithm;

import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.service.RegionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegionAlgorithmTest {
    @Autowired
    private RegionService regionService;

    @BeforeEach
    void init() throws CantCreateRegionException, InvalidDataException {
        regionService.createRegion("RegionNE", 20.0, 10.0, 10.0, 20.0);
        regionService.createRegion("RegionSW", -10.0, -20.0, -20.0, -10.0);
    }
    @Test
    public void createRegionIncludingFirstRegionTest() {
        // zawiera w sobie cały pierwszy region
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region2NE",30.0,5.0,5.0,25.0);
        });
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region2SW",-5.0,-30.0,-25.0,-5.0);
        });
    }

    @Test
    public void createOverlappingRegionTest() {
        // pokrywa część pierwszego regionu
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region3NE",30.0,15.0,15.0,30.0);
        });
        // pokrywa się jedna ściana regionu
        assertDoesNotThrow(() -> {
            regionService.createRegion("Region4NE",20.0,10.0,20.0,30.0);
        });

        // SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region3SW",-15.0,-30.0,-30.0,-15.0);
        });

        assertDoesNotThrow(() -> {
            regionService.createRegion("Region4SW",-10.0,-20.0,-30.0,-20.0);
        });
    }

    @Test
    public void createRegionInsideFirstRegionTest() {
        // region który zawiera się w pierwszym regionie
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region5NE",15.0,12.0,12.0,15.0);
        });
        //SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region5SW",-12.0,-15.0,-15.0,-12.0);
        });
    }

    @Test
    public void createSameAreaRegionTest() {
        //region pokrywa ten sam obszar
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region6NE", 20.0, 10.0, 10.0, 20.0);
        });
        //SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region6SW", -10.0, -20.0, -20.0, -10.0);
        });
    }
    @Test
    public void createSimilarRegionTest() {
        //region pokrywają się 2 przyległe boki
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region7NE", 20.0, 15.0, 10.0, 15.0);
        });
        //SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region7SW", -15.0, -20.0, -15.0, -10.0);
        });
    }

    @Test
    public void createRegionIncludingPartOfFirstRegionTest() {
        //region pokrywa kawałek pierwszego regionu i sciany sie nie pokrywają
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region8NE", 19.0, 5.0, 11.0, 15.0);
        });
        //SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region8SW", -5.0, -19.0, -15.0, -11.0);
        });
    }
    @Test
    public void createRegionIncludingThreeWallsOfFirstRegionTest() {
        // region zawiera 3 sciany regionu pierwszego
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region9NE", 20.0, 15.0, 10.0, 20.0);
        });
        //SW
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("Region9SW", -15.0, -20.0, -20.0, -10.0);
        });
    }
}