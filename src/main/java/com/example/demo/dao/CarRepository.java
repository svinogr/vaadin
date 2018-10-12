package com.example.demo.dao;

import com.example.demo.entity.cars.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
