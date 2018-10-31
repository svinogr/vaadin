package com.example.demo.services.serviceImpl;


import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.utils.search.CarSpecification;
import com.example.demo.entity.cars.utils.search.MyFilterItem;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

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
    public List<Car> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<Car> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            System.out.println(1 +" -"+myFilterItem.get().isChecked());
            Specification<Car> carSpecification = createSpecification(myFilterItem.get());
            resulList = carRepository.findAll(carSpecification, pageable).getContent();
            return resulList;
        }


        // List<Car> cars = carRepository.findAll(carSpecification);
        // long count = carRepository.count(carSpecification);
        //   System.out.println("spec car"+ cars.get(0).getPassportData().getId() + "_" +count);

//        if (myFilterItem.isPresent()) {
//            if (myFilterItem.get().isDate()) {
//
//            }
//            if (myFilterItem.get().isText()) {
//            }
//
//            if (myFilterItem.get().isCheck()) {
//                System.out.println("find check");
//                Car car = new Car();
//                GeneralData generalData = new GeneralData();
//                generalData.setFauly(true);
//                car.setGeneralData(generalData);
//             //  return carRepository.findAll(Example.of(car), pageable).getContent();
//               return carRepository.findByExample(1);
//            }
//        }

//        Pageable pageable = PageRequest.of(offset, limit,  Sort.by(Sort.Direction.ASC, "id"));
//        if(queryProperty != null){
//            Car c = new Car();
//            c.setId(Integer.parseInt(queryProperty[1]));
//            System.out.println("vnutri find");
//            Example<Car> example = Example.of(c);
//
//            return carRepository.findAll(example, pageable).getContent();
//        } else return carRepository.findAll(pageable).getContent();
        return Collections.emptyList();
    }

    private Specification<Car> createSpecification(MyFilterItem myFilterItem) {
        Specification<Car> specification = null;
        switch (myFilterItem.getEnumColumnNames()) {
            case FAULY:
                System.out.println("faulu spec "+myFilterItem.isChecked());
                specification = CarSpecification.generalByFauly(myFilterItem.isChecked());
                break;
        }
        return specification;


    }

//    @Override
//    public List<Car> findByExample(int offset, int limit) {
//        System.out.println(10);
//        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
//
//        return carRepository.findAll(pageable).getContent();
//
//    }


    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;
        if (myFilterItem.isPresent()) {
            Specification<Car> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(carRepository.count(specification));
        }
        return count;
//        Car car = new Car();
//        GeneralData generalData = new GeneralData();
//        generalData.setFauly(true);
//        car.setGeneralData(generalData);
//        // return Math.toIntExact(carRepository.count(Example.of(car)));
//        return 1;

//        if(queryProperty != null){
//            Car c = new Car();
//            c.setId(Integer.parseInt(queryProperty[1]));
//            System.out.println("fffffffffffffff");
//            Example<Car> example = Example.of(c);
//            long i = carRepository.count(example);
//            System.err.println("hhhhhhhjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+i);
//            return Math.toIntExact(i);
        //  } else return Math.toIntExact(carRepository.count());
        // return 0;
    }

    @Override
    public int getCount() {
        return Math.toIntExact(carRepository.count());
    }
}


