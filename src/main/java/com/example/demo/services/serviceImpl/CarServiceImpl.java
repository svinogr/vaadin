package com.example.demo.services.serviceImpl;


import com.example.demo.dao.CarDao;
import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public Car getById(long id)
    {
        return carRepository.getOne(id);
    }

    @Override
    public Car create(Car car) {
        Car save = carRepository.save(car);
        return  save;
    }

    @Override
    public Car createUser(Car car) {
        return null;
    }

    @Override
    public Car update(Car car) {
        Car save = carRepository.save(car);
        return  save;
    }

    @Override
    public boolean delete(Car car) {
        carRepository.delete(car);
        return true;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findById(long id) {
        Car car = new Car();
        car.setId(id);

        Example<Car> example = Example.of(car);
      return carRepository.findAll(example);

    }

}
