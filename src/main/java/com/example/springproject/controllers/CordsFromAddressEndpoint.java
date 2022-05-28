package com.example.springproject.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CordsFromAddressEndpoint {

    /* generate the api key from Google
    and add it as an environmental variable GOOGLE_APIKEY */

    @Value("${GOOGLE_APIKEY}")
    private String apiKey;

    @GetMapping("/cords")
    public List<Double> getCordsFromAddress(@RequestParam String address) {
        RestTemplate restTemplate = new RestTemplate();

        String url =  "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        String fetchedInfoObject = restTemplate.getForObject(url, String.class);
        JSONObject resultsObject = new JSONObject(fetchedInfoObject);

        if (resultsObject.getString("status").equals("ZERO_RESULTS")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location with given address found");
        }

        JSONObject cordsObject = resultsObject
                .getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        List<Double> cords = new ArrayList<>();

        cords.add(cordsObject.getDouble("lat"));
        cords.add(cordsObject.getDouble("lng"));

        return cords;
    }
}
