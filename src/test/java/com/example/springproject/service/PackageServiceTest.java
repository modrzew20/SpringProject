package com.example.springproject.service;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.ItemNotFoundException;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegionException;
import com.example.springproject.repository.repositoryExceptions.PackageNotFoundException;
import com.example.springproject.service.PackageService;

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
public class PackageServiceTest {

    @Autowired
    private PackageService packageService;

    @Autowired
    private CourierService courierService;



    @Test
    public void createPackageTest() throws NoCourierForThisRegionException, ItemNotFoundException {
        int size = packageService.allPackage().size();
        assertTrue(packageService.createPackage(2,1,"test street",100.0,"test","johnny",true, true));
        assertEquals(size + 1, packageService.allPackage().size());
    }

    @Test
    public void deletePackageTest() throws PackageNotFoundException {
        int size = packageService.allPackage().size();
        assertTrue(packageService.deletePackage(packageService.allPackage().get(0).getUuid()));
        assertEquals(size - 1, packageService.allPackage().size());

    }

    @Test
    public void findPackageTest() throws PackageNotFoundException {
        UUID uuid = packageService.allPackage().get(0).getUuid();
        assertEquals(uuid,
                packageService.findPackage(uuid).getUuid());
        assertThrows(PackageNotFoundException.class, () -> {
            packageService.findPackage(null);
        });
    }

    @Test
    public void getPackagesAssignToCourierTest() {
        UUID uuid = courierService.allCourier().get(0).getUuid();
        int size = 0;
        for (Package p : packageService.allPackage()) {
            if(p.getCourier().equals(uuid)) {
               size++;
            }
        }
        assertEquals(size, packageService.getPackagesAssignToCourier(uuid).size());
    }






}
