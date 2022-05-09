package com.example.springproject.repository;



import com.example.springproject.repository.repositoryExceptions.ItemNotFound;

import java.util.ArrayList;
import java.util.UUID;

interface AbstractRepo<T>  {

    ArrayList<T> getArrayList();

    boolean createElement(T element);

    boolean deleteElement(UUID uuid) throws ItemNotFound;

}
