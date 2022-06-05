package com.example.springproject.repository;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.PackageNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class PackageRepo implements AbstractRepo<Package> {
    ArrayList<Package> allPackage;

    public PackageRepo() {
        allPackage = new ArrayList<>();
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),0.1,0.1, UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c")));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef1"),51.759326535199506, 19.465191720442267, UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5")));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef2"),51.74555526529735, 19.461788737665167, UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5")));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef3"),51.74953852876642, 19.448254702637364, UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5")));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef4"),51.747057435234886, 19.438045090609112, UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5")));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),51.772184049166256, 19.448331806950346, UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5")));
    }

    public ArrayList<Package> all() {
        return allPackage;
    }

    public boolean create(Package item) {
        return allPackage.add(item);
    }

    public boolean delete(UUID uuid) throws PackageNotFoundException {
        boolean removed = allPackage.removeIf(x -> x.getUuid().equals(uuid));
        if (!removed) throw new PackageNotFoundException("Package doesn't exist");
        return removed;
    }

    public Package find(UUID uuid) throws PackageNotFoundException {
        return allPackage.stream().filter(pack -> pack.getUuid().equals(uuid)).findFirst()
                .orElseThrow(()-> new PackageNotFoundException("Package doesn't exist"));
    }

    public ArrayList<Package> couriersPackage(UUID uuid) {
        ArrayList<Package> result = new ArrayList<>();
        for (Package pack : allPackage) {
            if (pack.getCourier().equals(uuid)) result.add(pack);
        }
        return result;
    }

}
