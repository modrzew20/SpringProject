package com.example.springproject.service;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapsCoordinatesService {

    private final String apiKey = "AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig";

    public List<Double> getCordsRequest(String address) throws IOException, ResponseStatusException {
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
