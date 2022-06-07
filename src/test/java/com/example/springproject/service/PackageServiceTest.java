package com.example.springproject.service;

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

    @Test
    public void findPackageTest() throws PackageNotFoundException {
        assertEquals(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),
                packageService.findPackage(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5")).getUuid());
        assertThrows(PackageNotFoundException.class, () -> {
            packageService.findPackage(null);
        });
    }

    @Test
    public void getPackagesAssignToCourierTest() {
        assertEquals(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),
                packageService.getPackagesAssignToCourier(UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c")).get(0).getUuid());
    }

    @Test
    public void deletePackageTest() throws PackageNotFoundException {
        assertEquals(6, packageService.allPackage().size());
        assertTrue(packageService.deletePackage(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5")));
        assertEquals(5, packageService.allPackage().size());

    }

    @Test
    public void createPackageTest() throws NoCourierForThisRegionException, ItemNotFoundException {
        assertTrue(packageService.createPackage(2,1,"test street",100.0,"test","johnny",true, true));
        assertEquals(7, packageService.allPackage().size());
    }
}
