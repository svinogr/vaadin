package com.example.demo.services.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.EnumTypeOfBody;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.jornal.JournalItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;

public class PersonSpecification {
    private final static String PERSON_DATA_FIELD = "person";
    private final static String PERSON_CAR_DATA_FIELD = "car_person";
    public static final String SEARCH_FIELD_BIRTHDAY = "birthday";

    private static Predicate getCheckablePredicate(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.isChecked());
    }
    private static Predicate getDatablePredicate(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.between(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.getDateForSearch()[0], myFilterItem.getDateForSearch()[1]);
    }

    private static Predicate getTextablelePredicate(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.getTexForSearch()[0]);
    }
    private static Predicate getEnumTypeOfPerson(MyFilterItem myFilterItem, Root<Person> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                EnumTypePerson.valueOf(myFilterItem.getTexForSearch()[0]));
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
        return ((root, query, criteriaBuilder) -> getDatablePredicate(myFilterItem, root, criteriaBuilder, SEARCH_FIELD_BIRTHDAY));

    }

    public static Specification<Person> getBySyrname(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, "surname")));
    }

    public static Specification<Person> getByFired(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getCheckablePredicate(myFilterItem, root, criteriaBuilder, "fired")));
    }

    public static Specification<Person> getByTypePerson(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getEnumTypeOfPerson(myFilterItem, root, criteriaBuilder, "enumTypePerson")));
    }


    public static Specification<Person> getByCabinet(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, "cabinet")));

    }
}
