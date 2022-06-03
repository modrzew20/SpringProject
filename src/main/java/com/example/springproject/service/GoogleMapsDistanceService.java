package com.example.springproject.service;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;


public class GoogleMapsDistanceService {
    private final String my_key = "AIzaSyCYWcIJBZq1eS8sNcAACBkO0GJOXK8-5ig";

    private static String GET_URL = "https://maps.googleapis.com/maps/api/directions/json?";

    public GoogleMapsDistanceService() {
    }

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
