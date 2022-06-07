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
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),0.1,0.1,"address test", UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c"),"Kurier 1"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef1"),51.759326535199506, 19.465191720442267, "address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef2"),51.74555526529735, 19.461788737665167,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef3"),51.74953852876642, 19.448254702637364,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef4"),51.747057435234886, 19.438045090609112,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef6"),51.772184049166256, 19.448331806950346,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),0.1,0.1,"address test", UUID.fromString("88eef4a2-3ca5-4bcf-b569-6f2ec20f483c"),"Kurier 1"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef1"),51.7593265, 19.4651917, "address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef2"),51.74555526529735, 19.461788737665167,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef3"),51.74953852876642, 19.448254702637364,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef4"),51.747057435234886, 19.438045090609112,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
//        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef5"),51.772184049166256, 19.448331806950346,"address test", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef1"),51.759326535199506, 19.465191720442267, "al. Marszałka Józefa Piłsudskiego 15/23, 90-307 Łódź", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef2"),51.74555526529735, 19.461788737665167,"Piotrkowska 282, 90-001 Łódź", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef3"),51.74953852876642, 19.448254702637364,"aleje Politechniki 1, 93-590 Łódź", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef4"),51.747057435234886, 19.438045090609112,"Gen. Walerego Wróblewskiego 31, 93-566 Łódź", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
        this.create(new Package(UUID.fromString("2a9dc82c-bfc1-47db-b037-3569d3949ef6"),51.77207111833189, 19.446271862018023,"Więckowskiego 41, 90-734 Łódź", UUID.fromString("88eef4a2-5ff2-4bcf-baa9-9376ff8e26e5"),"Kurier Lodz"));
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
