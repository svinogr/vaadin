package com.example.demo.dao.daoimpl;


import com.example.demo.dao.CarDao;
import com.example.demo.entity.cars.car.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {
    public CarDaoImpl() {
        super(Car.class);
    }
}
