package com.example.demo.services;


import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.utils.search.MyFilterItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car getById(long id);

    Car create(Car car);

    Car createUser(Car car);

    Car update(Car car);

    boolean delete(Car car);

    List<Car> findAll();

    List<Car> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit);


    int getCount(Optional<MyFilterItem>  myFilterItem);

}
