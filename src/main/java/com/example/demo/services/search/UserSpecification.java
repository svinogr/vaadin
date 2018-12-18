package com.example.demo.services.search;

import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {
    public static Specification<User> getBySURNAME(MyFilterItem myFilterItem) {
        return (((root, query, criteriaBuilder) -> getTextablelePredicate(myFilterItem, root, criteriaBuilder, "surname")));
    }

    private static Predicate getTextablelePredicate(MyFilterItem myFilterItem, Root<User> root, CriteriaBuilder criteriaBuilder, String generalDataFieldOfUser) {
        Join<User, UserInfo> join = root.join("userInfo");
        return criteriaBuilder.equal(
                join.get(myFilterItem.getEnumColumnNamesFor().getColumnSearchName()),
                myFilterItem.getTexForSearch()[0]);
    }
}
