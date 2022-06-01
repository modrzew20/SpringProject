package com.example.springproject;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Region;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.PackageRepo;
import com.example.springproject.repository.RegionRepo;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegion;
import com.example.springproject.repository.repositoryExceptions.PackageNotFound;
import com.example.springproject.repository.repositoryExceptions.RegionNotFound;
import com.example.springproject.service.PackageService;
import com.example.springproject.service.RegionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.springproject.model.Package;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PackageServiceTest {

    private Package aPackage;
    private Courier aCourier;

    private UUID findPackageUUID;
    private UUID courierUUID;

    @Autowired
    private CourierRepo courierRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private PackageService packageService;

    @BeforeAll
    void setUpAll() {
        findPackageUUID = UUID.randomUUID();
        courierUUID = UUID.randomUUID();
        aCourier = new Courier(courierUUID, 32.4, 12.4,
                new Region(UUID.randomUUID(), "region", 43.4, 12.4, 43.5, 12.5));
        aPackage = new Package(findPackageUUID, 23.1, 40.0, aCourier);
        packageRepo.create(aPackage);
    }

    @Order(1)
    @Test
    public void findPackageTest() throws PackageNotFound {
        assertEquals(aPackage.getUuid(), packageService.findPackage(findPackageUUID).getUuid());
        assertThrows(PackageNotFound.class, () -> {
            packageService.findPackage(null);
        });
    }

    @Order(2)
    @Test
    public void createPackageTest() {

        assertEquals(packageService.getPackagesAssignToCourier(courierUUID).get(0).getUuid();
    }



}
