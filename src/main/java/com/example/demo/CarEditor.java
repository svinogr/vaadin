package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.entity.cars.car.EnumTypeOfBody;
import com.example.demo.entity.cars.car.GeneralData;
import com.example.demo.entity.cars.car.PassportData;
import com.example.demo.entity.cars.owner.Owner;
import com.example.demo.entity.users.User;
import com.example.demo.services.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
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
import java.util.EnumSet;
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
            Component pageToShown = mapTabs.get(tabs.getSelectedTab());
            for (Component page : mapTabs.values()) {
                if (page == pageToShown) {
                    page.setVisible(true);
                } else page.setVisible(false);

            }

        });

        add(tabs);
        Component pageToShown = mapTabs.get(tabs.getSelectedTab());
        for (Component page : mapTabs.values()) {
            add(page);
        }

    }

    private Tab createPassportTab() {
        Tab pasport = new Tab("Паспортные данные");
        VerticalLayout passportLayot = new VerticalLayout();

        HorizontalLayout oneLayoutH = new HorizontalLayout();
        TextField vin = new TextField("VIN");
        TextField modelTS = new TextField("Модель ТС");
        TextField typeTS = new TextField("Тип ТС");
        oneLayoutH.add(vin, modelTS, typeTS);
        passportLayot.add(oneLayoutH);

        HorizontalLayout twoLayoutH = new HorizontalLayout();
        TextField category = new TextField("Категория");
        DatePicker yearOfBuild = new DatePicker("Год выпуска");
        TextField modelOfEngine = new TextField("Модель двигателя");
        TextField eccoClass = new TextField("Эко класс");
        twoLayoutH.add(category, yearOfBuild, modelOfEngine, eccoClass);
        passportLayot.add(twoLayoutH);

        HorizontalLayout threeLayoutH = new HorizontalLayout();
        TextField numberOfEngine = new TextField("Двигатель №");
        TextField numberOfChassis = new TextField("Шасси №");
        TextField numberOfBody = new TextField("Кузов №");
        TextField color = new TextField("Цвет");
        threeLayoutH.add(numberOfEngine, numberOfChassis, numberOfBody, color);
        passportLayot.add(threeLayoutH);

        HorizontalLayout fourLayoutH = new HorizontalLayout();
        TextField powerOfEngine = new TextField("Мощность");
        TextField volumeOfEngine = new TextField("Обьем двтиг");
        TextField maxMass = new TextField("Макс. масса");
        TextField maxMassWithout = new TextField("Масса без нагрузки");
        fourLayoutH.add(powerOfEngine, volumeOfEngine, maxMass, maxMassWithout);
        passportLayot.add(fourLayoutH);

        HorizontalLayout fiveLayoutH = new HorizontalLayout();
        TextField builder = new TextField("Производитель");
        fiveLayoutH.add(builder);
        passportLayot.add(fiveLayoutH);

        HorizontalLayout sixLayoutH = new HorizontalLayout();
        TextField numberOfPassportTS = new TextField("Пасспорт №");
        DatePicker dateOfPassportTS = new DatePicker("Дата");
        TextField placeOfIssuanceOfPassportTS = new TextField("Кем выдан");
        sixLayoutH.add(numberOfPassportTS, dateOfPassportTS, placeOfIssuanceOfPassportTS);
        passportLayot.add(sixLayoutH);

        FlexLayout sevenLayoutH = new FlexLayout();
        sevenLayoutH.setAlignItems(Alignment.BASELINE);
        TextField owner = new TextField("Собственник");//TODO сделать запрос после смены владельца в базу
        Button addOwner = new Button(VaadinIcon.PLUS.create());
        addOwner.setEnabled(false);
        sevenLayoutH.add(owner, addOwner);
        passportLayot.add(sevenLayoutH);

        HorizontalLayout eightLayoutH = new HorizontalLayout();
        TextField cost = new TextField("Стоимость");
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setAlignItems(Alignment.BASELINE);
        TextField group = new TextField("Группа");//TODO сделать запрос после смены владельца в базу
        Button addGroup = new Button(VaadinIcon.PLUS.create());
        addGroup.setEnabled(false);
        flexLayout.add(group, addGroup);
        TextField regNumber = new TextField("Рег. знак");
        TextField oldregNumber = new TextField("Рег. знак старый");
        eightLayoutH.add(cost, flexLayout, regNumber, oldregNumber);
        passportLayot.add(eightLayoutH);

        HorizontalLayout nineLayoutH = new HorizontalLayout();
        TextField certificateOfRegistration = new TextField("Св-во №");
        TextField placeOfregistration = new TextField("Место регистрации");
        DatePicker dateOfRegistration = new DatePicker("Св-во дата ");
        DatePicker tempRegistration = new DatePicker("Временная регистрация до");
        passportLayot.add(certificateOfRegistration, placeOfregistration, dateOfRegistration, tempRegistration);

        HorizontalLayout tenLayout = new HorizontalLayout();
        ComboBox<EnumTypeOfBody> typeOfBody = new ComboBox<>();
        typeOfBody.setLabel( "Тип кузова");
        typeOfBody.setItems(EnumSet.allOf(EnumTypeOfBody.class));
        TextField quantityOfPallet = new TextField("Кол-во палет");
        TextField lenghtOfBody = new TextField("Длина");
        TextField widhtOfBody = new TextField("Ширина");
        TextField heightOfBody = new TextField("Высота");
        TextField volumeOfBody = new TextField("Обьем фургона куб");
        tenLayout.add(typeOfBody, quantityOfPallet, lenghtOfBody, widhtOfBody, heightOfBody, volumeOfBody);
        passportLayot.add(tenLayout);

        passportLayot.setVisible(false);
        pasport.add(passportLayot);
        mapTabs.put(pasport, passportLayot);

        return pasport;
    }

    private void setupBinder() {
//        binder.bindInstanceFields(this);

    }

    private Tab createGeneralTab() {
        Tab tab = new Tab("Главное");

        VerticalLayout oneLayout = new VerticalLayout();

        HorizontalLayout subOneLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        DatePicker dateOfTakeToBalanse = new DatePicker();
        dateOfTakeToBalanse.setLabel("Дата приема на баланс");

        binder.forField(dateOfTakeToBalanse).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
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

        Checkbox decommissioned = new Checkbox("Списан");
        binder.forField(decommissioned).bind(new ValueProvider<Car, Boolean>() {
            @Override
            public Boolean apply(Car car) {
                return car.getGeneralData().isDecommissioned();
            }
        }, new Setter<Car, Boolean>() {
            @Override
            public void accept(Car car, Boolean aBoolean) {
                car.getGeneralData().setDecommissioned(aBoolean);
            }
        });


        DatePicker dateOfdecommissioned = new DatePicker("Дата списания");
        dateOfdecommissioned.setEnabled(false);
        decommissioned.addValueChangeListener((event) -> {
            dateOfdecommissioned.setEnabled(event.getValue());
        });

        binder.forField(dateOfdecommissioned).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getGeneralData().getDateOfdecommissioned() == null ? null
                                : car.getGeneralData().getDateOfdecommissioned().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getGeneralData().setDateOfdecommissioned(date);
                    }
                });


        Checkbox fauly = new Checkbox("неисправный");
        binder.forField(decommissioned).bind(new ValueProvider<Car, Boolean>() {
            @Override
            public Boolean apply(Car car) {
                return car.getGeneralData().isFauly();
            }
        }, new Setter<Car, Boolean>() {
            @Override
            public void accept(Car car, Boolean aBoolean) {
                car.getGeneralData().setFauly(aBoolean);
            }
        });

        subOneLayoutH.add(dateOfTakeToBalanse, fauly, dateOfdecommissioned, decommissioned, dateOfdecommissioned);
        oneLayout.add(subOneLayoutH);

        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        TextField podrazdelenieOrGarage = new TextField("Подразделение (гараж)");
        binder.forField(podrazdelenieOrGarage).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getPodrazdelenieOrGarage();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setPodrazdelenieOrGarage(s);
            }
        });

        TextField colonna = new TextField("Коллона");
        binder.forField(colonna).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getColonna();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setColonna(s);
            }
        });



        TextField numberOfGarage = new TextField("Гаражный номер");
        binder.forField(numberOfGarage).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getNumberOfGarage();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setNumberOfGarage(s);
            }
        });

        TextField numberOfInventar = new TextField("Инвентаризационный номер");
        binder.forField(numberOfInventar).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getNumberOfInventar();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setNumberOfInventar(s);
            }
        });

        subTwoLayoutH.add(podrazdelenieOrGarage,numberOfGarage, numberOfInventar, colonna);
        oneLayout.add(subTwoLayoutH);
//

        TextField comment = new TextField("Комментарий");
        binder.forField(comment).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getComment();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setComment(s);
            }
        });

        TextField typeOfFuel = new TextField("Вид топлива");
        binder.forField(typeOfFuel).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getGeneralData().getTypeOfFuel();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setTypeOfFuel(s);
            }
        });


        HorizontalLayout subThreeLayoutV = new HorizontalLayout();
        TextField mileage = new TextField("Пробег");
        binder.forField(mileage).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getGeneralData().getMileage());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setMileage(Double.parseDouble(s));
            }
        });

        DatePicker dateOfMileage = new DatePicker("Дата пробега");
        binder.forField(dateOfMileage).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getGeneralData().getDateOfMileage() == null ? null
                                : car.getGeneralData().getDateOfMileage().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getGeneralData().setDateOfMileage(date);
                    }
                });



        TextField mashineHours = new TextField("Моточасы кран/доп.об");
        binder.forField(mashineHours).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getGeneralData().getMashineHours());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getGeneralData().setMileage(Integer.parseInt(s));
            }
        });

        subThreeLayoutV.add(typeOfFuel, mileage, dateOfMileage, mashineHours);
        oneLayout.add(subThreeLayoutV);


        oneLayout.add(comment);

        oneLayout.setVisible(true);
        add(oneLayout);

        VerticalLayout subFourLayout = new VerticalLayout();
        Checkbox trailer = new Checkbox("Прицеп");
        Grid<Car> trailers = new Grid<>();
        trailers.setSelectionMode(Grid.SelectionMode.SINGLE);
        trailers.addColumn(c -> (c.getId())).setHeader("id");
        trailers.addColumn(c -> (c.getGeneralData().getNumberOfGarage())).setHeader("Гаражный номер");

        trailer.addValueChangeListener(event -> {
            trailers.setVisible(!event.getValue());
        });
        subFourLayout.add(trailer, trailers);
        oneLayout.add(subFourLayout);


        HorizontalLayout subFiveLayoutV = new HorizontalLayout();
        HorizontalLayout subSixLayoutH = new HorizontalLayout();

        TextField numberOfTahograf = new TextField("Номер тахографа");
        TextField modelOfTahograf = new TextField("Модель тахографа");
        DatePicker dateOfPoverkaTohograf = new DatePicker("Поверка тахографа до");
        DatePicker dateOfCalibdrateTohograf = new DatePicker("Калибровка тахографа");
        subFiveLayoutV.add(numberOfTahograf, modelOfTahograf);
        subSixLayoutH.add(dateOfPoverkaTohograf, dateOfCalibdrateTohograf);
        oneLayout.add(subFiveLayoutV, subSixLayoutH);

        TextField platon = new TextField("Платон");
        oneLayout.add(platon);

        mapTabs.put(tab, oneLayout);

        return tab;
    }

    public void setChangeHandler(CarEditor.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public void save() {
        carService.create(car);
        changeHandler.onChange();
    }

    @Transactional
    public void deleteCar() {
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
