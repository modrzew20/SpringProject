package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringProjectApplication.class, args);
        //GoogleMapsService googleMapsService = new GoogleMapsService();
        //System.out.print(googleMapsService.getDistanceRequest("Bardowskiego, 19a, Pabianice", "Sempołowskiej, 6, Pabianice"));

    }

}
