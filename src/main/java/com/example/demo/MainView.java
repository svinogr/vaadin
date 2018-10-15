package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.users.User;
import com.example.demo.services.CarService;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@HtmlImport("styles/styles.html")
@Route(value = "main")
//@Route(value = "login")
public class MainView extends VerticalLayout {

    @Autowired
    LoginService loginService;

    private static final String NAME_OF_MENU_GENERAL = "Основные";
    private static final String MENU_ITEM_LOGOUT = "Выход";
    public static final String ADD_BTN_TEXT = "Добавить";
    public static final String SEARCH_TEXT_PLACEHOLDER = "поиск";

    private CarService carService;
    private Button addBtn;
    private TextField searchField;
    private Grid<Car> grid;

    public MainView(CarService carService) {
        this.carService = carService;
        createMenu();
        createGreedWithCars();
        updateListItems();
    }

    private void updateListItems() {
        List<Car> cars;
        if (!searchField.isEmpty()) {
            long id = Long.parseLong(searchField.getValue());
            cars = carService.findById(id);
        } else{
            cars = carService.findAll();
        }
        grid.setItems(cars);
    }

    private void createMenu() {
        HorizontalLayout menulayout = new HorizontalLayout();

        ComboBox<String> mainListBoxMenu = new ComboBox<>();
        mainListBoxMenu.setLabel(NAME_OF_MENU_GENERAL);
        mainListBoxMenu.addValueChangeListener(event -> {
            String eventName = event.getValue();
           switch (eventName){
               case MENU_ITEM_LOGOUT:
                   loginService.logout();
                   UI.getCurrent().getPage().reload();
                //   getUI().ifPresent(ui -> ui.navigate("login"));
                   break;
               case "":

                   break;
           }

        });
        mainListBoxMenu.setItems(MENU_ITEM_LOGOUT);
        menulayout.add(mainListBoxMenu);
        add(menulayout);
    }

    private void createGreedWithCars() {
        HorizontalLayout greedMenuLayout = new HorizontalLayout();
        FlexLayout searchLayout = new FlexLayout();
        addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());

        searchField = new TextField();
        searchField.setPlaceholder(SEARCH_TEXT_PLACEHOLDER);
        searchField.addValueChangeListener(e -> updateListItems());
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        Button clearBtn = new Button(VaadinIcon.CLOSE.create());
        clearBtn.addClickListener(event -> {
            searchField.clear();
            updateListItems();
        });

        searchLayout.add(searchField, clearBtn);
        greedMenuLayout.add(addBtn, searchLayout);

        grid = new Grid<>();
    //    grid.setItems(cars);
        grid.addColumn(Car::getId).setHeader("Id");
        //  grid.addColumn(Car::getOwner).setHeader("Пароль");
        // grid.addColumn(User::getRole)
        //       .setHeader("Роль");

        add(greedMenuLayout, grid);
    }
}
