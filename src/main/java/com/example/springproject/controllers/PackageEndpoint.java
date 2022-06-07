package com.example.springproject.controllers;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.CourierNotFoundException;
import com.example.springproject.repository.repositoryExceptions.ItemNotFoundException;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegionException;
import com.example.springproject.repository.repositoryExceptions.PackageNotFoundException;
import com.example.springproject.service.GoogleMapsService;
import com.example.springproject.service.PackageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PackageEndpoint {

    @Autowired
    private PackageService packageService;

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/package")
    @CrossOrigin(origins = "*")
    ArrayList<Package> allPackage() {
        return packageService.allPackage();
    }

    @PostMapping("/package")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> createPackage (
            @RequestParam @NonNull String address,
            @RequestParam double cashOnDelivery,
            @RequestParam String account,
            @RequestParam String accountOwner,
            @RequestParam boolean smsNotification,
            @RequestParam boolean fragile) {
        boolean status = false;
        try {
            List<Double> coords = googleMapsService.getCordsRequest(address);
            String fullAddress = googleMapsService.getFullAddress(address);
            status = packageService.createPackage(coords.get(1),coords.get(0),fullAddress, cashOnDelivery, account, accountOwner, smsNotification, fragile);
        } catch (NoCourierForThisRegionException | ItemNotFoundException e ) {
            return ResponseEntity.status(405).body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/package/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> deletePackage(@PathVariable("uuid") UUID uuid) {
        boolean status;
        try {
            status = packageService.deletePackage(uuid);
        } catch (PackageNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/package/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> findPackage(@PathVariable("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(packageService.findPackage(uuid).toString());
        } catch (PackageNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

    @GetMapping("/package/courier/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<ArrayList<Package>> packagesAssignedInOrderToCourier(@PathVariable("uuid") UUID uuid) throws IOException, CourierNotFoundException {
        try {
            System.out.println(uuid);
            return ResponseEntity.status(200).body(googleMapsService.getOptimalRoute(uuid));
        } catch (CourierNotFoundException | IOException e) {
            return ResponseEntity.status(405).build();
        }
    }
}
