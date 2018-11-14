package com.example.demo.entity.cars.utils.search;

import com.example.demo.entity.jornal.JournalItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public class JournalSpecification {
    private final static String JORNAL_DATA_FIELD = "journal_item";

    public static Specification<JournalItem> getByIdParent(long id){
        return  ((root, query, criteriaBuilder) -> {
            Path<JournalItem> car_id = root.get("car_id");
            return criteriaBuilder.equal( car_id, id);
        });
    }
}
