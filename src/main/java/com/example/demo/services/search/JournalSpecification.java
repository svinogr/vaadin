package com.example.demo.services.search;

import com.example.demo.entity.jornal.JournalItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JournalSpecification {
    private final static String JORNAL_DATA_FIELD = "journal_item";

    private static Predicate getCheckablePredicate(MyFilterItem myFilterItem, Root<JournalItem> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfCar) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(
                root.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.isChecked()),
                criteriaBuilder.equal(
                  root.get( "car_id"),
                          myFilterItem.getParentIdForSearch()));
    }

    public static Specification<JournalItem> getByIdParent(long id){
        return  ((root, query, criteriaBuilder) -> {
            Path<JournalItem> car_id = root.get("car_id");
            return criteriaBuilder.equal( car_id, id);
        });
    }

    public static Specification<JournalItem> getByClosed(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getCheckablePredicate(myFilterItem, root, criteriaBuilder, "closed")));

    }
}
