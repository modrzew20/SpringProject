package com.example.springproject.controllers;

import com.example.springproject.model.Courier;
import com.example.springproject.repository.repositoryExceptions.CantCreateCourier;
import com.example.springproject.repository.repositoryExceptions.CourierNotFound;
import com.example.springproject.repository.repositoryExceptions.NoCourierForThisRegion;
import com.example.springproject.repository.repositoryExceptions.RegionNotFound;
import com.example.springproject.service.CourierService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class CourierEndpoint {

    @Autowired
    private CourierService courierService;

    @GetMapping("/courier")
    ArrayList<Courier> allRegion() {
        return courierService.allCourier();
    }

    @PostMapping("/courier")
    ResponseEntity<String> createRegion(
            @RequestParam @NonNull double startPointX,
            @RequestParam @NonNull double startPointY,
            @RequestParam @NonNull UUID regionUUID
    ){
        boolean status;
        try {
            status = courierService.createCourier(startPointX, startPointY, regionUUID);
        } catch (CantCreateCourier | RegionNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(201).build();
        else return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/courier/{uuid}")
    ResponseEntity<String> deleteRegion(@PathParam("uuid") UUID uuid) {
        boolean status;
        try {
            status = courierService.deleteCourier(uuid);
        } catch (CourierNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
        if (status) return ResponseEntity.status(200).build();
        else return ResponseEntity.status(400).build();
    }

    @GetMapping("/courier/{uuid}")
    ResponseEntity<String> findRegion(@PathParam("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(courierService.findCourier(uuid).toString());
        } catch (CourierNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

    @GetMapping("/courier/region/{uuid}")
    ResponseEntity<String> courierAssignToRegion(@PathParam("uuid") UUID uuid) {
        try {
            return ResponseEntity.status(200).body(courierService.getAssignCourierForRegion(uuid).toString());
        } catch (NoCourierForThisRegion | RegionNotFound e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }


}
