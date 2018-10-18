package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.car.PassportData;
import com.example.demo.entity.cars.owner.Owner;
import com.example.demo.entity.users.User;
import com.example.demo.services.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringComponent
@UIScope
public class CarEditor extends VerticalLayout {
    private Car car;
    private CarService carService;
    private Map<Tab, Component> mapTabs = new HashMap<>();
    private Binder<Car> binder = new Binder<>(Car.class);
    private Tabs tabs;
    private ChangeHandler changeHandler;


    // @Autowired
    public CarEditor(CarService carService) {
        this.carService = carService;
        createTab();
        setupBinder();
    }

    private void createTab() {
        Tab general = createGeneralTab();
        Tab pasport = createPassportTab();
        tabs = new Tabs();
        tabs.add(general, pasport);

        tabs.addSelectedChangeListener(event -> {
            System.out.println("hjhfkehfkjef");
            Component pageToShown = mapTabs.get(tabs.getSelectedTab());
            for (Component page: mapTabs.values()){

                if(page == pageToShown ) {
                    page.setVisible(true);
                }else page.setVisible(false);

            }

        });

        add(tabs);
        Component pageToShown = mapTabs.get(tabs.getSelectedTab());
        for (Component page: mapTabs.values()){
            add(page);
        }

    }

    private Tab createPassportTab() {
        Tab pasport = new Tab("Паспортные данные");
        VerticalLayout oneLayout = new VerticalLayout();
        oneLayout.add(new Label("tab2"));
        mapTabs.put(pasport, oneLayout);

        return pasport;

    }

    private void setupBinder() {
//        binder.bindInstanceFields(this);

    }

    private Tab createGeneralTab() {
        Tab tab = new Tab("Главное");
      //  Div pageOne = new Div();
        VerticalLayout oneLayout = new VerticalLayout();

        VerticalLayout subOneLayoutV = new VerticalLayout();
        HorizontalLayout subOneLayoutH = new HorizontalLayout();

        DatePicker dateOfTakeToBalanse = new DatePicker();
        dateOfTakeToBalanse.setLabel("Дата приема на баланс");

//        TextField dateOfTakeToBalanse = new TextField("Дата приема на баланс");
        binder.forField(dateOfTakeToBalanse).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car ) {
                        return car.getGeneralData().getDateOfTakeToBalanse() == null ? null
                                : car.getGeneralData().getDateOfTakeToBalanse().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getGeneralData().setDateOfTakeToBalanse(date);
                    }
                });
        //TODO можно добавить пикер даты
        Checkbox decommissioned = new Checkbox("Списан");
        TextField dateOfdecommissioned = new TextField("Дата списания");
        dateOfdecommissioned.setEnabled(false);
        Checkbox fauly = new Checkbox("неисправный");
        subOneLayoutH.add(dateOfTakeToBalanse, decommissioned, dateOfdecommissioned);
        subOneLayoutV.add(subOneLayoutH, fauly);

        oneLayout.add(subOneLayoutV);


        TextField podrazdelenieOrGarage = new TextField("Подразделение (гараж)");
        TextField colonna = new TextField("Коллона");
        oneLayout.add(podrazdelenieOrGarage, colonna);

        VerticalLayout subTwoLayoutV = new VerticalLayout();
        TextField numberOfGarage = new TextField("Гаражный номер");
        TextField numberOfInventar = new TextField("Инвентаризационный номер");
        subTwoLayoutV.add(numberOfGarage, numberOfInventar);
        oneLayout.add(subTwoLayoutV);

        TextField comment = new TextField("Комментарий");
        TextField typeOfFuel = new TextField("Вид топлива");
        oneLayout.add(comment, typeOfFuel);

        VerticalLayout subThreeLayoutV = new VerticalLayout();
        TextField mileage = new TextField("Пробег");
        TextField dateOfMileage = new TextField("Дата пробега");
        subThreeLayoutV.add(mileage, dateOfMileage);
        oneLayout.add(subThreeLayoutV);


        TextField mashineHours = new TextField("Моточасы кран/доп.об");
        oneLayout.add(mashineHours);
        oneLayout.setVisible(true);
        add(oneLayout);
       // pageOne.add(oneLayout);
       // pageOne.setText("defef");
       // pageOne.setVisible(true);
        mapTabs.put(tab, oneLayout);

        return tab;
    }

    public void setChangeHandler(CarEditor.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public void save(){
        carService.create(car);
        changeHandler.onChange();
    }

@Transactional
    public  void deleteCar(){
       carService.delete(car);
        System.out.println(car.getId());
       changeHandler.onChange();
    }

    @Transactional
    public void editCar(Car c) {
        if (c == null) {
            setVisible(false);
            return;
        }

        boolean persisted = c.getId() != 0;
        if (persisted) {
            System.out.println("ddededededed" + c.getId());
            // Find fresh entity for editing
            car = carService.getById(c.getId());

        } else {
            car = c;
            Owner owner = new Owner();
            PassportData passportData = new PassportData();
            GeneralData generalData = new GeneralData();
//            car.setOwner(owner);
            car.setGeneralData(generalData);
          //  car.setPassportData(passportData);
            // user.setLogin("");
        }

        System.out.println("frfrfffdddddddddd"+car.getGeneralData().getDateOfTakeToBalanse());
        //cancel.setVisible(persisted);
       // cancel.setVisible(true);
      //  delete.setVisible(persisted);

        // Bind user properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(car);

//        binder.forField(login)
//                //.withValidator(new StringLengthValidator("не должен быть пустым", 1, 50))
//                .withValidator(new Validator<String>() {
//                    @Override
//                    public ValidationResult apply(String s, ValueContext valueContext) {
//                        System.out.println("-"+s+"-");
//                        if(s.isEmpty()){
//                            System.out.println(1);
//                            save.setEnabled(false);
//                            return ValidationResult.error("не должен быть пустым");
//                        } else {
//                            save.setEnabled(true);
//                            return ValidationResult.ok();}
//                    }
//                })
//                .bind(User::getLogin, User::setLogin);

        setVisible(true);

        // Focus first name initially
        //login.focus();
    }

    public interface ChangeHandler {
        void onChange();
    }


}
