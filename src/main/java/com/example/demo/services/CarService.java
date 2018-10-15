package com.example.demo.services;


import com.example.demo.entity.cars.car.Car;

import java.util.List;

public interface CarService {
    Car getById(long id);

    Car create(Car car);

    Car createUser(Car car);

    Car update(Car car);

    boolean delete(Car car);

    List<Car> findAll();

    List<Car> findById(long id);

}
