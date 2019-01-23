package com.example.demo.upload.editor;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.upload.excell.CarUploadExcelItem;
import com.vaadin.annotations.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
@Push
@Route("push")
public class CarUploadEditor extends AbstractUploadEditor<Car> {
    public CarUploadEditor(CarUploadExcelItem uploadable) {
        super(uploadable);
    }
}
