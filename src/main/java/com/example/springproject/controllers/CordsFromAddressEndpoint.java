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

    /* generate the api key here https://positionstack.com/
    and add it as an environmental variable */

    @Value("${APIKEY}")
    private String apiKey;

    @GetMapping("/cords")
    public List<Double> getCordsFromAddress(@RequestParam String address) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.positionstack.com/v1/forward?access_key=" + apiKey
                + "&query=" + address;
        String fetchedCordsObject = restTemplate.getForObject(url, String.class);
        JSONArray cordsArray = new JSONObject(fetchedCordsObject).getJSONArray("data");

        List<Double> cords = new ArrayList<>();

        if (cordsArray.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location with given address found");
        }

        if (cordsArray.length() > 1) {
            throw new ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "Multiple locations found");
        }

        if (!cordsArray.getJSONObject(0).getString("name").equals(null)
                && !cordsArray.getJSONObject(0).get("postal_code").equals(null)) {

            cords.add(cordsArray.getJSONObject(0).getDouble("latitude"));
            cords.add(cordsArray.getJSONObject(0).getDouble("longitude"));

            return cords;

        } else {
            throw new ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "Ambiguous location");
        }
    }
}
