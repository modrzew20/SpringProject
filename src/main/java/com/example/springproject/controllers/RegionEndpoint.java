package com.example.springproject.controllers;

import com.example.springproject.model.Region;
import com.example.springproject.repository.repositoryExceptions.CantCreateRegion;
import com.example.springproject.repository.repositoryExceptions.RegionNotFound;
import com.example.springproject.service.RegionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class RegionEndpoint {

    @Autowired
    private RegionService regionService;

    @GetMapping("/region")
    public ArrayList<Region> allRegion() {
        return regionService.allRegion();
    }

    @PostMapping("/region")
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
        } catch (CantCreateRegion e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/region/{uuid}")
    ResponseEntity<String> deleteRegion(@PathParam("uuid") UUID uuid) {
        boolean status;
        try {
            status = regionService.deleteRegion(uuid);
        } catch (RegionNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/region/{uuid}")
    ResponseEntity<String> findRegion(@PathParam("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(regionService.findRegion(uuid).toString());
        } catch (RegionNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }


}
