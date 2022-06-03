package com.example.springproject.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleMapsService {

    private static String GET_URL = "https://maps.googleapis.com/maps/api/directions/json?";

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

    public int getDistanceRequest(String origin, String destination) throws IOException {

        GET_URL = GET_URL + "origin=" + origin + "&destination=" + destination + "&key=" + apiKey;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(GET_URL)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonObject = new JSONObject(response.body().string());

        return jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").optJSONObject(0).optJSONObject("distance").getInt("value");
    }
}
