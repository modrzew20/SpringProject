package com.example.springproject.model;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class Region {

    @Generated @Getter
    UUID uuid;

    @Getter
    @Setter
    @NonNull
    String name;

    @Getter @NonNull
    final double N_limit;

    @Getter @NonNull
    final double S_limit;

    @Getter @NonNull
    final double W_limit;

    @Getter @NonNull
    final double E_limit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Double.compare(region.N_limit, N_limit) == 0 && Double.compare(region.S_limit, S_limit) == 0 && Double.compare(region.W_limit, W_limit) == 0 && Double.compare(region.E_limit, E_limit) == 0 && Objects.equals(uuid, region.uuid) && name.equals(region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, N_limit, S_limit, W_limit, E_limit);
    }
}
