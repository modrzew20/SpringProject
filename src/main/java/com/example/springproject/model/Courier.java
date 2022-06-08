package com.example.springproject.model;

import lombok.*;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@ToString
public class Courier {

    @Getter
    UUID uuid;

    @Getter @NonNull
    String name;

    @Getter @Setter @NonNull
    String address;

    @Getter @Setter @NonNull
    UUID region;

    @Getter @Setter @NonNull
    String regionName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(uuid, courier.uuid) && region.equals(courier.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, region);
    }
}
