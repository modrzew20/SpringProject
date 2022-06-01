package com.example.springproject.repository;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import com.example.springproject.repository.repositoryExceptions.PackageNotFound;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class PackageRepo {
    ArrayList<Package> allPackege;

    public PackageRepo() {
        allPackege = new ArrayList<>();
    }

    public ArrayList<Package> all() {
        return allPackege;
    }

    public boolean create(Package item) {
        return allPackege.add(item);
    }

    public boolean delete(UUID uuid) throws PackageNotFound {
        boolean removed = allPackege.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new PackageNotFound("Package doesn't exist");
        return removed;
    }

    public Package find(UUID uuid) throws PackageNotFound {
        return allPackege.stream().filter(pack -> pack.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()-> new PackageNotFound("Package doesn't exist"));
    }

    public ArrayList<Package> couriersPackage(UUID uuid) {
        ArrayList<Package> result = new ArrayList<>();
        for (Package pack : allPackege) {
            if (pack.getCourier().getUuid().equals(uuid)) result.add(pack);
        }
        return result;
    }

}
