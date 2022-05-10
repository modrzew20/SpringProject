package com.example.springproject.controllers;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.service.PackageService;
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
        return packageService.allPackage();
    }

    @PostMapping("/packages")
    ResponseEntity<String> create(
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



}
