package com.example.demo.entity.cars.utils.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.GeneralData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;

public class CarSpecification {
    private CarSpecification() {
    }
    public static Specification<Car> byId(int id){
        return (root, query, criteriaBuilder) -> {
            int idS = id;
            Join<Car, GeneralData>  join = root.join("generalData");
            return criteriaBuilder.equal(join.get("id"),idS);
        };
    }

    public static Specification<Car> generalByFauly(boolean check){
        return ((root, query, criteriaBuilder) -> {
            Join<Car, GeneralData> join = root.join("generalData");
            return  criteriaBuilder.equal(join.get("fauly"), check);
        });

    }}
