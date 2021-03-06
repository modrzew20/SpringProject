package com.example.springproject.model;

import lombok.*;

import java.util.Objects;
import java.util.UUID;


@ToString
@Builder
@AllArgsConstructor
public class Package {



    @Getter
    UUID uuid;

    @Getter @NonNull
    private final double x_coordinate;

    @Getter @NonNull
    private final double y_coordinate;

    @Getter @NonNull
    private final String address;

    @Getter @Setter
    private UUID courier;

    @Getter @Setter
    private String courierName;

    @Getter @Setter
    private double cashOnDelivery;

    @Getter @Setter
    private String account;

    @Getter @Setter
    private String accountOwner;

    @Getter @Setter
    private boolean smsNotification;

    @Getter @Setter
    private boolean fragile;

    public Package(UUID uuid, double x_coordinate, double y_coordinate, String address, UUID courier, String courierName) {
        this.uuid = uuid;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.address = address;
        this.courier = courier;
        this.courierName = courierName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Double.compare(aPackage.x_coordinate, x_coordinate) == 0 && Double.compare(aPackage.y_coordinate, y_coordinate) == 0 && Objects.equals(uuid, aPackage.uuid) && Objects.equals(courier, aPackage.courier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, x_coordinate, y_coordinate, courier);
    }
}
