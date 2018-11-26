package com.example.demo.services.search;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.organisation.Organisation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class OrganisationSpecification {

    private static Predicate getTextablelePredicate(MyFilterItem myFilterItem, Root<Organisation> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.getTexForSearch()[0]);
    }

    //TODO поменять четвертый параметр на гетсечебл из майайтем
    public static Specification<Organisation> getByName(MyFilterItem myFilterItem) {
        return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"name")));
    }

    public static Specification<Organisation> getByOgrn(MyFilterItem myFilterItem) {
        return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"ogrn")));
    }

    public static Specification<Organisation> getByOkpo(MyFilterItem myFilterItem) {
        return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"okpo")));
    }

    public static Specification<Organisation> getByEgrul(MyFilterItem myFilterItem) {
        return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"egrul")));
    }

    public static Specification<Organisation> getByInn(MyFilterItem myFilterItem) {
         return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"inn")));
    }

    public static Specification<Organisation> getByKpp(MyFilterItem myFilterItem) {
        return (((root, criteriaQuery, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root,
                criteriaBuilder,"kpp")));
    }

}
