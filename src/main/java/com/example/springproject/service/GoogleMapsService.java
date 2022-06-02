package com.example.springproject.service;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;


public class GoogleMapsService {
    private final String my_key = "AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig";

    //https://maps.googleapis.com/maps/api/directions/json?origin=Pabianice,Bardowskiego19a&destination=Warszawska%2075,%2095-200%20Pabianice&waypoints=optimize:true|Bugaj%20110,%2095-200%20Pabianice|Zamkowa%2031,%2095-200%20Pabianice&key=AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig

    private static String GET_URL = "https://maps.googleapis.com/maps/api/directions/json?";


    public int getDistanceRequest(String origin, String destination) throws IOException {

        GET_URL = GET_URL + "origin=" + origin + "&destination=" + destination + "&key=" + my_key;

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
