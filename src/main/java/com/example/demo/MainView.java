package com.example.demo;

import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.users.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "main")
public class MainView extends VerticalLayout {

    CarRepository carRepository;
    public MainView(CarRepository carRepository) {
this.carRepository = carRepository;
        List<Car> cars = carRepository.findAll();
        add(new Label("main"));
        Grid<Car> grid = new Grid<>();
        grid.setItems(cars);
        grid.addColumn(Car::getId).setHeader("Id");
      //  grid.addColumn(Car::getOwner).setHeader("Пароль");
       // grid.addColumn(User::getRole)
         //       .setHeader("Роль");

        add(grid);


    }
}
