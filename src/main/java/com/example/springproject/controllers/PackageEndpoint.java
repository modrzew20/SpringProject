package com.example.springproject.controllers;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.service.PackageService;
import com.example.springproject.service.serviceExceptions.RegionNotFoundException;
import lombok.NonNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageEndpoint {

    @Autowired
    private PackageService packageService;

    @GetMapping("/packages")
    List<Package> all() {
        return packageService.all();
    }

    @PostMapping("/packages")
    ResponseEntity<String> create(
            @RequestParam @NonNull double x_coords,
            @RequestParam @NonNull double y_coords) {
        boolean status;
        try {
            status = packageService.create(x_coords,y_coords);
        } catch (RegionNotFoundException | NoCourierForThisRegion e ) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }



}
