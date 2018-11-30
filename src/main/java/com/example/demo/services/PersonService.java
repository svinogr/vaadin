package com.example.demo.services;

import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.search.MyFilterItem;

import java.util.List;
import java.util.Optional;

public interface PersonService extends ItemService<Person> {
    int getCountByParentId(long parentId);
    List<Person> findAllByParentId(long ParentId, int offset, int limit);
}
