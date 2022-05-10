package com.example.springproject.model;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Builder
public class Package {

    @Generated
    @Getter
    UUID uuid;

    @Getter @NonNull
    private final double x_coordinate;

    @Getter @NonNull
    private final double y_coordinate;

    @Getter @Setter
    private Courier courier;

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
