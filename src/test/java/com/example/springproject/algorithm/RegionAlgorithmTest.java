package com.example.springproject.algorithm;

import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.service.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegionAlgorithmTest {
    @Autowired
   private RegionService regionService;

    @BeforeEach
    void init() throws CantCreateRegionException, InvalidDataException {
        regionService.createRegion("first", 20.0, 10.0, 10.0, 20.0);
    }

    @Test
    public void createRegionIncludingFirstRegionTest() {
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("second",30.0,5.0,5.0,25.0);
        });
    }

    @Test
    public void createOverlappingRegionTest() {
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("name2",30.0,15.0,5.0,30.0);
        });
    }
    @Test
    public void createRegionTest() {
        assertThrows(CantCreateRegionException.class, () -> {
            regionService.createRegion("name2",30.0,25.0,40.0,50.0);
        });
        assertDoesNotThrow( () -> {
            regionService.createRegion("name2",30.0,25.0,40.0,45.0);
        });
    }
}
