package com.example.springproject.controllers;

import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegionException;
import com.example.springproject.repository.repositoryExceptions.InvalidDataException;
import com.example.springproject.repository.repositoryExceptions.RegionNotFoundException;
import com.example.springproject.service.RegionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class RegionEndpoint {

    @Autowired
    private RegionService regionService;

    @GetMapping("/region")
    @CrossOrigin(origins = "*")
    public ArrayList<Region> allRegion() {
        return regionService.allRegion();
    }

    @PostMapping("/region")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createRegion(
            @RequestParam @NonNull String name,
            @RequestParam @NonNull double N_limit,
            @RequestParam @NonNull double S_limit,
            @RequestParam @NonNull double W_limit,
            @RequestParam @NonNull double E_limit
    ){
        boolean status;
        try {
            status = regionService.createRegion(name, N_limit, S_limit, W_limit, E_limit);
        } catch (CantCreateRegionException | InvalidDataException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/region/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> deleteRegion(@PathVariable("uuid") UUID uuid) {
        boolean status;
        try {
            status = regionService.deleteRegion(uuid);
        } catch (RegionNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/region/{uuid}")
    @CrossOrigin(origins = "*")
    ResponseEntity<String> findRegion(@PathVariable("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(regionService.findRegion(uuid).toString());
        } catch (RegionNotFoundException e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

    @GetMapping("/freeregion")
    @CrossOrigin(origins = "*")
    ResponseEntity<ArrayList<Region>> getRegionsWithNoCourier() {
        return ResponseEntity.status(200).body(regionService.getRegionsWithNoCourier());
    }



}
