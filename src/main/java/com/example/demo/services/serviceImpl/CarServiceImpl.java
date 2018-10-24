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
import java.util.Optional;

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
    public List<Car> findByExample(Optional<String> car, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit,  Sort.by(Sort.Direction.ASC, "id"));
        if(car.isPresent()){
            Car c = new Car();
            c.setId(Integer.parseInt(car.get()));
            System.out.println("vnutri find");
            Example<Car> example = Example.of(c);

            return carRepository.findAll(example, pageable).getContent();
        } else return carRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Car> findByExample(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit,  Sort.by(Sort.Direction.ASC, "id"));

            return carRepository.findAll(pageable).getContent();

    }


    @Override
    public int getCount(Optional<String> car) {

        if(car.isPresent()){
            Car c = new Car();
            c.setId(Integer.parseInt(car.get()));
            System.out.println("fffffffffffffff");
            Example<Car> example = Example.of(c);
            long i = carRepository.count(example);
            System.err.println("hhhhhhhjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+i);
            return Math.toIntExact(i);
        } else return Math.toIntExact(carRepository.count());

    }
    @Override
    public int getCount() {
        return Math.toIntExact(carRepository.count());
    }
}


