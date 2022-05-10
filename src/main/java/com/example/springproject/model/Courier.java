package com.example.springproject.model;

import lombok.*;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class Courier {

    @Generated
    @Getter
    UUID uuid;

    @Getter @Setter @NonNull
    double startPointX;

    @Getter @Setter @NonNull
    double startPointY;

    @Getter @Setter @NonNull
    Region region;

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
