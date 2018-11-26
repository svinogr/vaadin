package com.example.demo.services;

import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.search.MyFilterItem;

import java.util.List;
import java.util.Optional;

public interface OrganisationService {
    int getCountByParentId(long parentId);
    List<Organisation> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit);

    int getCount(Optional<MyFilterItem>  myFilterItem);
    List<Organisation> findAllByParentId(long ParentId, int offset, int limit);

    Organisation create(Organisation person);
    Organisation update(Organisation person);
    boolean delete(Organisation person);
    Organisation getById(long id);
}
