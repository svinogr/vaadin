package com.example.demo;

import com.example.demo.dao.CarRepository;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.users.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "main")
//@Route(value = "login")
public class MainView extends VerticalLayout {

    private CarRepository carRepository;
    private Button addBtn;
    private TextField searchField;

    public MainView(CarRepository carRepository) {
        this.carRepository = carRepository;
        createMenu();
        createGreedWithCars();
    }

    private void createMenu() {
        HorizontalLayout menulayout = new HorizontalLayout();

        ComboBox<Button> mainListBoxMenu = new ComboBox<>();
        mainListBoxMenu.setLabel("General");

        Button button = new Button("test");
        Button button2 = new Button("test2");

        mainListBoxMenu.setItems(button, button2);
        menulayout.add(mainListBoxMenu);
        add(menulayout);
    }

    private void createGreedWithCars() {
        addBtn = new Button("Добавить новую машину", VaadinIcon.PLUS.create());

        searchField = new TextField();
        searchField.setPlaceholder("найти");

        List<Car> cars = carRepository.findAll();
        add(new Label("main"));

        Grid<Car> grid = new Grid<>();
        grid.setItems(cars);
        grid.addColumn(Car::getId).setHeader("Id");
        //  grid.addColumn(Car::getOwner).setHeader("Пароль");
        // grid.addColumn(User::getRole)
        //       .setHeader("Роль");

        add(addBtn, grid);
    }
}
