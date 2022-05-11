package com.example.springproject.controllers;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.repository.repositoryExceptions.PackageNotFound;
import com.example.springproject.service.PackageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class PackageEndpoint {

    @Autowired
    private PackageService packageService;

    @GetMapping("/package")
    ArrayList<Package> allPackage() {
        return packageService.allPackage();
    }

    @PostMapping("/package")
    ResponseEntity<String> createPackage (
            @RequestParam @NonNull double x_coords,
            @RequestParam @NonNull double y_coords) {
        boolean status;
        try {
            status = packageService.createPackage(x_coords,y_coords);
        } catch (NoCourierForThisRegion | ItemNotFound e ) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/package/{uuid}")
    ResponseEntity<String> deletePackage(@PathParam("uuid") UUID uuid) {
        boolean status;
        try {
            status = packageService.deletePackage(uuid);
        } catch (PackageNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/package/{uuid}")
    ResponseEntity<String> findPackage(@PathParam("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(packageService.findPackage(uuid).toString());
        } catch (PackageNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

    @GetMapping("/package/courier/{uuid}")
    ArrayList<Package> packageAssignToCourier(@PathParam("uuid") UUID uuid) {
        return packageService.getPackagesAssignToCourier(uuid);
    }
}
