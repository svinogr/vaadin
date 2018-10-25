package com.example.demo.services;


import com.example.demo.entity.cars.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car getById(long id);

    Car create(Car car);

    Car createUser(Car car);

    Car update(Car car);

    boolean delete(Car car);

    List<Car> findAll();

    List<Car> findByExample(String car, int offset, int limit);
    List<Car> findByExample(int offset, int limit);

    int getCount(String car);

    int getCount();


}
