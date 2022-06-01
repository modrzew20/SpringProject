package com.example.springproject.repository;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.PackageNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class PackageRepo implements AbstractRepo<Package> {
    ArrayList<Package> allPackege;



    public PackageRepo() {
        allPackege = new ArrayList<>();
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),0.1,0.1,UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c")));
    }

    public ArrayList<Package> all() {
        return allPackege;
    }

    public boolean create(Package item) {
        return allPackege.add(item);
    }

    public boolean delete(UUID uuid) throws PackageNotFoundException {
        boolean removed = allPackege.removeIf(x -> x.getUuid().equals(uuid));
        if (!removed) throw new PackageNotFoundException("Package doesn't exist");
        return removed;
    }

    public Package find(UUID uuid) throws PackageNotFoundException {
        return allPackege.stream().filter(pack -> pack.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()-> new PackageNotFoundException("Package doesn't exist"));
    }

    public ArrayList<Package> couriersPackage(UUID uuid) {
        ArrayList<Package> result = new ArrayList<>();
        for (Package pack : allPackege) {
            if (pack.getCourier().equals(uuid)) result.add(pack);
        }
        return result;
    }

}
