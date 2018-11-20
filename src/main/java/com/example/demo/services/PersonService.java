package com.example.demo.services;

import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.search.MyFilterItem;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    int getCountByParentId(long parentId);
    List<Person> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit);

    int getCount(Optional<MyFilterItem>  myFilterItem);
    List<Person> findAllByParentId(long ParentId, int offset, int limit);

    Person create(Person person);
    Person update(Person person);
    boolean delete(Person person);
    Person getById(long id);
}
