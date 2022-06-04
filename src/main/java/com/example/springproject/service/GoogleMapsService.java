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

    private static final String GET_URL = "https://maps.googleapis.com/maps/api/directions/json?";

    private final String apiKey = "AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig";

    @Autowired
    CourierRepo courierRepo;

    @Autowired
    PackageService packageService;

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

        assert response.body() != null;
        JSONObject jsonObject = new JSONObject(response.body().string());

        return jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").optJSONObject(0).optJSONObject("distance").getInt("value");
    }

    public ArrayList<Package> getOptimalRoute(UUID uuid) throws IOException, CourierNotFoundException {

        ArrayList<Package> packages = packageService.getPackagesAssignToCourier(uuid);

        StringBuilder url = new StringBuilder(GET_URL + "origin=" + courierRepo.find(uuid).getStartPointX() + "," +  courierRepo.find(uuid).getStartPointY()
                + "&destination=" +  courierRepo.find(uuid).getStartPointX() + "," +  courierRepo.find(uuid).getStartPointY()
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
        assert response.body() != null;
        JSONObject jsonObject = new JSONObject(response.body().string());

        if(jsonObject.getString("status").equals("ZERO_RESULTS")){
            throw new CourierNotFoundException("Can't find optimal route for packages with given addresses");
        }

        JSONArray waypoint_order = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("waypoint_order");

        ArrayList<Package> sortedPackages = new ArrayList<>();

        for (int i = 0; i < waypoint_order.length(); i++) {
            sortedPackages.add(i, packages.get((int) waypoint_order.get(i)));
        }

        return sortedPackages;
    }
}
