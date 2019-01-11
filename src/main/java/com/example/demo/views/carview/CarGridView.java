package com.example.demo.views.carview;

import com.example.demo.editors.CarEditor;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CarGridView extends AbstractGridView<Car> {

    public CarGridView(CarService carService, CarEditor carEditor) {
        super(carService, carEditor, Car.class);
    }

    @Override
    protected Car createNewInstanceItem() {
        return new Car();
    }

    @Override
    protected void createGrid() {
        grid = new Grid();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(car -> car.isTrack() == true ? "Прицеп" : "Транспорт").setHeader("Тип");
        grid.addColumn(car -> car.getGeneralData().getDateOfTakeToBalanse() == null ? "" : dateFormat(car.getGeneralData().getDateOfTakeToBalanse())).setHeader("Дата принятия на баланс").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getRegNumber()).setHeader("Рег.знак").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("VIN").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getTypeTS()).setHeader("Тип ТС").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getNumberOfGarage()).setHeader("Номер Гаража").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пробег").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getEccoClass()).setHeader("ЭКО клас");
        add(grid);
    }
}
