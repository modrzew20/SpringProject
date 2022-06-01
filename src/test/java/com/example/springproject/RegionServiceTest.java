package com.example.springproject;

import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.repository.repositoryExceptions.RegionNotFoundException;
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

    @Autowired
    private RegionService regionService;

    @Order(1)
    @Test
    public void findRegionTest() throws RegionNotFoundException {
        assertEquals("Region1", regionService.findRegion(UUID.fromString("26699969-59b4-42f1-8c70-6d403a522767")).getName());
        assertThrows(RegionNotFoundException.class, () -> {
            regionService.findRegion(null);
        });
    }

    @Order(2)
    @Test
    public void createRegionTest() {
        assertThrows(InvalidDataException.class, () -> {
            regionService.createRegion("name", 30.0, 50.1, 43.2, 32.1);
        });
        assertDoesNotThrow(() -> {
            regionService.createRegion("name", 50.3, 50.1, 32.2, 42.1);
        });
    }

    @Order(3)
    @Test
    public void deleteRegionTest() throws RegionNotFoundException {
        assertTrue(regionService.deleteRegion(UUID.fromString("4c6d7ecc-efc3-44ce-95e8-44182f86362b")));
        assertThrows(RegionNotFoundException.class, () -> {
            regionService.deleteRegion(UUID.fromString("4c6d7ecc-efc3-44ce-95e8-44182f86362b"));
        });
    }

}
