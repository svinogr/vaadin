package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class CarExcelItem extends AbstractExcelItem<Car> {
    public CarExcelItem(CarService itemService) {
        super(itemService);
    }
}
