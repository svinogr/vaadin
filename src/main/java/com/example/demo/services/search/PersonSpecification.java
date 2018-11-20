package com.example.demo.services.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.personal.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;

public class PersonSpecification {
    private final static String PERSON_DATA_FIELD = "person";
    private final static String PERSON_CAR_DATA_FIELD = "car_person";

    private static Predicate getCheckablePredicate(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        return criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesForCar().getColumnSearchName()),
                myFilterItem.isChecked());
    }
    private static Predicate getDatablePredicate(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.between(
                root.get(myFilterItem.getEnumColumnNamesForCar().getColumnSearchName()),
                myFilterItem.getDateForSearch()[0], myFilterItem.getDateForSearch()[1]);
    }

    private static Predicate getTextablelePredicate(MyFilterItem myFilterItem, Root<Car> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        Join<Car, GeneralData> join = root.join(generalDataFieldOfCar);
        System.out.println( myFilterItem.getTexForSearch()[0]);
        return criteriaBuilder.equal(
                join.get(myFilterItem.getEnumColumnNamesForCar().getColumnSearchName()),
                myFilterItem.getTexForSearch()[0]);
    }


    public static Specification<Person> getByIdParent(long id) {
        return ((root, query, cb) -> {
            query.distinct(true);
            Root<Person> personRoot = root;
            Root<Car> carRoot  = query.from(Car.class);
            Expression<Collection<Person>> persons = carRoot.get("cars");
            return cb.and(cb.equal(carRoot.get("id"), carRoot), cb.isMember(personRoot, persons));

        });
    }

    public static Specification<Person> getByDateBirth(MyFilterItem myFilterItem) {
        return ((root, query, criteriaBuilder) -> getDatablePredicate(myFilterItem, root, criteriaBuilder, "birthday"));

    }
}
