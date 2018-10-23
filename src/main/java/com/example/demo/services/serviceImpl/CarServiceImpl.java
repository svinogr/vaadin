package com.example.demo.services.serviceImpl;


import com.example.demo.dao.CarDao;
import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public Car getById(long id) {
        return carRepository.findById(id).get();// getOne(id);
    }

    @Override
    public Car create(Car car) {
        System.out.println(car);
        Car save = carRepository.save(car);
        return save;
    }

    @Override
    public Car createUser(Car car) {
        return null;
    }

    @Override
    public Car update(Car car) {
        Car save = carRepository.save(car);
        return save;
    }

    @Transactional
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
    public List<Car> findByExample(Car car, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit,  Sort.by(Sort.Direction.ASC, "id"));
        if(car != null){
            Example<Car> example = Example.of(car);
            return carRepository.findAll(example, pageable).getContent();
        } else return carRepository.findAll(pageable).getContent();
    }

    @Override
    public int getCount(Car car) {
        if(car != null){
            Example<Car> example = Example.of(car);
            return Math.toIntExact(carRepository.count(example));
        } else return Math.toIntExact(carRepository.count());

    }
}


