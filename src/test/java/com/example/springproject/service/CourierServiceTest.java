package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Region;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.*;
import com.example.springproject.service.CourierService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourierServiceTest {

    @Autowired
    private CourierService courierService;

    @Test
    public void findCourierTest() throws CourierNotFoundException {
        assertEquals(UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c"), courierService.findCourier
                (UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c")).getUuid());
        assertThrows(CourierNotFoundException.class, () -> {
            courierService.findCourier(null);
        });
    }

    @Test
    public void createCourierTest() {
        int size = courierService.allCourier().size();
        assertDoesNotThrow(() -> {
            courierService.createCourier("test",34.2, 30.0,
                    UUID.fromString("b6d7bb81-732a-490e-bdf3-d3993bfe882b"));
        });
        assertEquals(size + 1, courierService.allCourier().size());

        assertThrows(CantCreateCourierException.class, () -> {
            courierService.createCourier("test",34.2, 30.0,
                    UUID.fromString("b6d7bb81-732a-490e-bdf3-d3993bfe882b"));
        });

        assertThrows(RegionNotFoundException.class, () -> {
            courierService.createCourier("test",34.2, 30.0,
                    UUID.fromString("c0f09f98-9b22-4580-9c3d-a4273bf6327a"));
        });

    }

    @Test
    public void getAssignCourierForRegionTest() throws RegionNotFoundException, NoCourierForThisRegionException {
        assertThrows(NoCourierForThisRegionException.class, () -> {
            courierService.getAssignCourierForRegion(UUID.fromString("df1941d5-20b3-48ae-9186-cd37b47aac37"));
        });
        assertEquals(UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c"),
                courierService.getAssignCourierForRegion(UUID.fromString("26699969-59b4-42f1-8c70-6d403a522767")).getUuid());
    }

    @Test
    public void deleteCourierTest() throws CourierNotFoundException {
        int size = courierService.allCourier().size();
        assertTrue(courierService.deleteCourier(UUID.fromString("1b12b987-b96a-4c17-9428-9376ff8e26e5")));
        assertEquals(size - 1, courierService.allCourier().size());
    }
}
