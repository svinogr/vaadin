package com.example.demo.services;

import com.example.demo.entity.cars.personal.Person;

import java.util.List;

public interface PersonService extends ItemService<Person> {
    int getCountByParentId(long parentId);

    List<Person> findAllByParentId(long ParentId, int offset, int limit);
}
