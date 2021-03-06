package com.example.springproject.controllers;

import com.example.springproject.model.Courier;
import com.example.springproject.repository.repositoryExceptions.CantCreateCourierException;
import com.example.springproject.repository.repositoryExceptions.CourierNotFoundException;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegionException;
import com.example.springproject.repository.repositoryExceptions.RegionNotFoundException;
import com.example.springproject.service.CourierService;
import com.example.springproject.service.GoogleMapsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class CourierEndpoint {

    @Autowired
    private CourierService courierService;

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/courier")
    @CrossOrigin(origins = "*")
    ArrayList<Courier> allCourier() {
        return courierService.allCourier();
    }

    @PostMapping("/courier")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> createCourier(
            @RequestParam("name") @NonNull String name,
            @RequestParam @NonNull String address,
            @RequestParam @NonNull UUID regionUUID
    ){
        boolean status;
        String fullAddress = googleMapsService.getFullAddress(address);
        try {
            status = courierService.createCourier(name,fullAddress, regionUUID);
        } catch (CantCreateCourierException | RegionNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/courier/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> deleteCourier(@PathVariable("uuid") UUID uuid) {
        boolean status;
        try {
            status = courierService.deleteCourier(uuid);
        } catch (CourierNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/courier/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> findCourier(@PathVariable("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(courierService.findCourier(uuid).toString());
        } catch (CourierNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

    @GetMapping("/courier/region/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> courierAssignedToRegion(@PathVariable("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(courierService.getAssignCourierForRegion(uuid).toString());
        } catch (NoCourierForThisRegionException | RegionNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }


}
