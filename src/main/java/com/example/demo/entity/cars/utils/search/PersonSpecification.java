package com.example.demo.entity.cars.utils.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.personal.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class PersonSpecification {
    private final static String PERSON_DATA_FIELD = "person";
    private final static String PERSON_CAR_DATA_FIELD = "car_person";

    public static Specification<Person> getByIdParent(long id) {
        return ((root, query, cb) -> {
            query.distinct(true);
            Root<Person> personRoot = root;
            Root<Car> carRoot  = query.from(Car.class);
            Expression<Collection<Person>> persons = carRoot.get("cars");
            return cb.and(cb.equal(carRoot.get("id"), carRoot), cb.isMember(personRoot, persons));

        });
    }
}
