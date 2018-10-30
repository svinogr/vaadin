package com.example.demo.dao;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.utils.search.MyFilterItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select c from cars  where c.id_general_data in (select general_data.id from general_data where general_data.fauly = :o)", nativeQuery = true)
    List<Car> findByExample(@Param("o") int i);

}
