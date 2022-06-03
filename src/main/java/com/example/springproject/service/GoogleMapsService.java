package com.example.springproject.service;

import com.example.springproject.model.Courier;
import com.example.springproject.model.Package;
import com.example.springproject.repository.CourierRepo;
import com.example.springproject.repository.repositoryExceptions.CourierNotFoundException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GoogleMapsService {

    private static String GET_URL = "https://maps.googleapis.com/maps/api/directions/json?";

    private final String apiKey = "AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig";

    @Autowired
    CourierRepo courierRepo;

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

        String url = GET_URL + "origin=" + origin + "&destination=" + destination + "&key=" + apiKey;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonObject = new JSONObject(response.body().string());

        return jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").optJSONObject(0).optJSONObject("distance").getInt("value");
    }

    public ArrayList<Package> getOptimalRoute(ArrayList<Package> packages) throws IOException, CourierNotFoundException {

        Courier courier = courierRepo.find(packages.get(0).getCourier());

        StringBuilder url = new StringBuilder(GET_URL + "origin=" + courier.getStartPointX() + "," + courier.getStartPointY()
                + "&destination=" + courier.getStartPointX() + "," + courier.getStartPointY()
                + "&waypoints=optimize:true|");

        for (Package i : packages) {
            url.append(i.getX_coordinate()).append(",").append(i.getY_coordinate()).append("|");
        }
        url.append("&key=" + apiKey);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.valueOf(url))
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonObject = new JSONObject(response.body().string());

        JSONArray waypoint_orderJSONArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("waypoint_order");

        int[] waypoint_order = new int[waypoint_orderJSONArray.length()];

        for (int i=0; i<waypoint_order.length; i++)
        {
            waypoint_order[i] = (int) waypoint_orderJSONArray.get(i);
        }

        ArrayList<Package> sortedPackages = new ArrayList<>();

        for (int i = 0; i < waypoint_order.length; i++) {
            sortedPackages.add(i, packages.get(waypoint_order[i]));
        }

        return sortedPackages;
    }
}
