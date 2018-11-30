package com.example.demo.services;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.tabs.Tab;

import java.util.List;
import java.util.Optional;

public interface ItemService<T> {
    T getById(long id);
    T create(T item);
    T update(T item);
    boolean delete(T item);
    List<T> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit);
    int getCount(Optional<MyFilterItem>  myFilterItem);
}
