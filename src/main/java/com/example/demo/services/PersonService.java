package com.example.demo.services;

import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.jornal.JournalItem;

import java.util.List;

public interface PersonService {

    int getCountByParentId(long parentId);
    int getCount();
    List<Person> findAllByParentId(long ParentId, int offset, int limit);
    List<Person> findAll();
    Person create(Person person);
    Person update(Person person);
    boolean delete(Person person);
    Person getById(long id);
}
