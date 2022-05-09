package com.example.springproject.repository;

import com.example.springproject.model.Package;
import com.example.springproject.repository.repositoryExceptions.ItemNotFound;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class PackageRepo implements AbstractRepo<Package> {
    ArrayList<Package> allPackege;

    @Override
    public ArrayList<Package> getArrayList() {
        return allPackege;
    }

    @Override
    public boolean createElement(Package element) {
        return allPackege.add(element);
    }

    @Override
    public boolean deleteElement(UUID uuid) throws ItemNotFound {
        boolean removed = allPackege.removeIf(x -> x.getUuid()==uuid);
        if (!removed) throw new ItemNotFound("Item didn't found");
        return removed;
    }
}
