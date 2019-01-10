package com.example.demo.editors;

import com.example.demo.entity.cars.car.*;
import com.example.demo.services.CarService;
import com.example.demo.services.UniqTestInterface;
import com.example.demo.validators.BigDecimalValidator;
import com.example.demo.validators.DoubleValidator;
import com.example.demo.validators.IntegerValidator;
import com.example.demo.validators.UniqTextValidator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.EnumSet;

@SpringComponent
@UIScope
public class CarEditorG extends AbstarctEditor<Car> {

    public CarEditorG(CarService itemService) {
        super(itemService);
    }

    @Override
    void setTitle() {
        title.setText("Карточка транспорта");
    }

    @Override
    void createTabs(Tabs tabs) {
        Tab general = createGeneralTab();
        Tab pasport = createPassportTab();
        tabs.add(general, pasport);
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
        binder.forField(fauly).bind(new ValueProvider<Car, Boolean>() {
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

        subTwoLayoutH.add(podrazdelenieOrGarage, numberOfGarage, numberOfInventar, colonna);
        oneLayout.add(subTwoLayoutH);

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

        ComboBox<EnumTypeFuel> typeOfFuel = new ComboBox<>("Вид топлива");
        typeOfFuel.setItems(EnumTypeFuel.values());
        binder.forField(typeOfFuel).bind(new ValueProvider<Car, EnumTypeFuel>() {
            @Override
            public EnumTypeFuel apply(Car car) {
                return car.getGeneralData().getTypeOfFuel();
            }
        }, new Setter<Car, EnumTypeFuel>() {
            @Override
            public void accept(Car car, EnumTypeFuel enumTypeFuel) {
                car.getGeneralData().setTypeOfFuel(enumTypeFuel);
            }
        });

        HorizontalLayout subThreeLayoutV = new HorizontalLayout();
        TextField mileage = new TextField("Пробег");
        binder.forField(mileage)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(mileage, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
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
        binder.forField(mashineHours)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(mashineHours, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
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

        //  VerticalLayout subFourLayout = new VerticalLayout();
        Checkbox trailer = new Checkbox("Прицеп");
        binder.forField(trailer).bind(new ValueProvider<Car, Boolean>() {
            @Override
            public Boolean apply(Car car) {
                return car.isTrack();
            }
        }, new Setter<Car, Boolean>() {
            @Override
            public void accept(Car car, Boolean aBoolean) {
                car.setTrack(aBoolean);
            }
        });
        oneLayout.add(trailer, comment);

        oneLayout.setVisible(true);
        add(oneLayout);

        HorizontalLayout subFiveLayoutV = new HorizontalLayout();
        HorizontalLayout subSixLayoutH = new HorizontalLayout();

        TextField numberOfTahograf = new TextField("Номер тахографа");
        binder.forField(numberOfTahograf)
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return car.getGeneralData().getNumberOfTahograf();
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getGeneralData().setNumberOfTahograf(s);
                    }
                });

        TextField modelOfTahograf = new TextField("Модель тахографа");
        binder.forField(modelOfTahograf)
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return car.getGeneralData().getModelTahograf();
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getGeneralData().setModelTahograf(s);
                    }
                });
        DatePicker dateOfPoverkaTohograf = new DatePicker("Поверка тахографа до");
        binder.forField(dateOfPoverkaTohograf).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getGeneralData().getDateOfPoverkaTahograf() == null ? null
                                : car.getGeneralData().getDateOfPoverkaTahograf().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getGeneralData().setDateOfPoverkaTahograf(date);
                    }
                });

        DatePicker dateOfCalibdrateTohograf = new DatePicker("Калибровка тахографа");
        binder.forField(dateOfCalibdrateTohograf).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getGeneralData().getDateCalibrOfTahograf() == null ? null
                                : car.getGeneralData().getDateCalibrOfTahograf().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getGeneralData().setDateCalibrOfTahograf(date);
                    }
                });
        subFiveLayoutV.add(numberOfTahograf, modelOfTahograf);
        subSixLayoutH.add(dateOfPoverkaTohograf, dateOfCalibdrateTohograf);
        oneLayout.add(subFiveLayoutV, subSixLayoutH);

        TextField platon = new TextField("Платон");
        binder.forField(platon)
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return car.getGeneralData().getPlaton();
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getGeneralData().setPlaton(s);
                    }
                });
        oneLayout.add(platon);

        HorizontalLayout subEightLayoutH = new HorizontalLayout();
        subEightLayoutH.setWidth("400px");

        TextField chancged = new TextField("Изменено пользователем");
        chancged.setEnabled(false);
        binder.forField(chancged)
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return car.getChanged() == null ? "" : car.getChanged();
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {

                    }
                });


        subEightLayoutH.add(chancged);
        chancged.setWidth("400px");
        oneLayout.add(subEightLayoutH);
        oneLayout.setAlignSelf(Alignment.END, subEightLayoutH);
        mapTabs.put(tab, oneLayout);

        return tab;
    }

    private Tab createPassportTab() {
        Tab pasport = new Tab("Паспортные данные");
        VerticalLayout passportLayot = new VerticalLayout();

        HorizontalLayout oneLayoutH = new HorizontalLayout();
        TextField vin = new TextField("VIN");
        if (item != null) {
            binder.forField(vin).
                    withValidator(new UniqTextValidator((UniqTestInterface) itemService, binder.getBean().getId()))
                    .bind(new ValueProvider<Car, String>() {
                        @Override
                        public String apply(Car car) {
                            return car.getPassportData().getVin();
                        }
                    }, new Setter<Car, String>() {
                        @Override
                        public void accept(Car car, String s) {
                            car.getPassportData().setVin(s);
                        }
                    });
        } else {

            binder.forField(vin)
                    .bind(new ValueProvider<Car, String>() {
                        @Override
                        public String apply(Car car) {
                            return car.getPassportData().getVin();
                        }
                    }, new Setter<Car, String>() {
                        @Override
                        public void accept(Car car, String s) {
                            car.getPassportData().setVin(s);
                        }
                    });
        }
        TextField modelTS = new TextField("Модель ТС");
        binder.forField(modelTS).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getModelTS();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setModelTS(s);
            }
        });

        TextField typeTS = new TextField("Тип ТС");
        binder.forField(typeTS).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getTypeTS();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setTypeTS(s);
            }
        });
        oneLayoutH.add(vin, modelTS, typeTS);
        passportLayot.add(oneLayoutH);

        HorizontalLayout twoLayoutH = new HorizontalLayout();

        TextField category = new TextField("Категория");
        binder.forField(category).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getCategory();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setCategory(s);
            }
        });

        DatePicker yearOfBuild = new DatePicker("Год выпуска");
        binder.forField(yearOfBuild).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getPassportData().getYearOfBuild() == null ? null
                                : car.getPassportData().getYearOfBuild().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getPassportData().setYearOfBuild(date);
                    }
                });

        TextField modelOfEngine = new TextField("Модель двигателя");
        binder.forField(modelOfEngine).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getModelOfEngine();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setModelOfEngine(s);
            }
        });

        ComboBox<Integer> eccoClass = new ComboBox<>("Эко класс");
        eccoClass.setItems(1, 2, 3, 4, 5);
        binder.forField(eccoClass)
                .bind(new ValueProvider<Car, Integer>() {
                    @Override
                    public Integer apply(Car car) {
                        return car.getPassportData().getEccoClass() == 0 ? null : car.getPassportData().getEccoClass();
                    }
                }, new Setter<Car, Integer>() {
                    @Override
                    public void accept(Car car, Integer s) {
                        car.getPassportData().setEccoClass(s);
                    }
                });
        twoLayoutH.add(category, yearOfBuild, modelOfEngine, eccoClass);
        passportLayot.add(twoLayoutH);

        HorizontalLayout threeLayoutH = new HorizontalLayout();

        TextField numberOfEngine = new TextField("Двигатель №");
        binder.forField(numberOfEngine).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getNumberOfEngine();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setNumberOfEngine(s);
            }
        });

        TextField numberOfChassis = new TextField("Шасси №");
        binder.forField(numberOfChassis).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getNumberOfChassis();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setNumberOfChassis(s);
            }
        });

        TextField numberOfBody = new TextField("Кузов №");
        binder.forField(numberOfBody).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getNumberOfBody();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setNumberOfBody(s);
            }
        });

        TextField color = new TextField("Цвет");
        binder.forField(color).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getColor();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setColor(s);
            }
        });
        threeLayoutH.add(numberOfEngine, numberOfChassis, numberOfBody, color);
        passportLayot.add(threeLayoutH);

        HorizontalLayout fourLayoutH = new HorizontalLayout();

        TextField powerOfEngine = new TextField("Мощность");
        binder.forField(powerOfEngine)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(powerOfEngine, status);
                    setEnableSubmit();
                }).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getPassportData().getPowerOfEngine());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setPowerOfEngine(Integer.parseInt(s));
            }
        });

        TextField volumeOfEngine = new TextField("Обьем двиг");
        binder.forField(volumeOfEngine)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(volumeOfEngine, status);
                    setEnableSubmit();
                }).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getPassportData().getVolumeOfEngine());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setVolumeOfEngine(Integer.parseInt(s));
            }
        });
        TextField maxMass = new TextField("Макс. масса");
        binder.forField(maxMass)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(maxMass, status);
                    setEnableSubmit();
                }).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getPassportData().getMaxMass());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setMaxMass(Integer.parseInt(s));
            }
        });
        TextField maxMassWithout = new TextField("Масса без нагрузки");
        binder.forField(maxMassWithout)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(maxMassWithout, status);
                    setEnableSubmit();
                }).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getPassportData().getMaxMassWithout());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setMaxMassWithout(Integer.parseInt(s));
            }
        });
        fourLayoutH.add(powerOfEngine, volumeOfEngine, maxMass, maxMassWithout);
        passportLayot.add(fourLayoutH);

        HorizontalLayout fiveLayoutH = new HorizontalLayout();
        TextField builder = new TextField("Производитель");
        binder.forField(builder).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getBuilder();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setBuilder(s);
            }
        });
        fiveLayoutH.add(builder);
        passportLayot.add(fiveLayoutH);

        HorizontalLayout sixLayoutH = new HorizontalLayout();
        TextField numberOfPassportTS = new TextField("Пасспорт №");
        binder.forField(numberOfPassportTS).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getNumberOfPassportTS();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setNumberOfPassportTS(s);
            }
        });
        DatePicker dateOfPassportTS = new DatePicker("Дата");
        binder.forField(dateOfPassportTS).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getPassportData().getDateOfPassportTS() == null ? null
                                : car.getPassportData().getDateOfPassportTS().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getPassportData().setDateOfPassportTS(date);
                    }
                });
        TextField placeOfIssuanceOfPassportTS = new TextField("Кем выдан");
        binder.forField(placeOfIssuanceOfPassportTS).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getPlaceOfIssuanceOfPassportTS();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setPlaceOfIssuanceOfPassportTS(s);
            }
        });
        sixLayoutH.add(numberOfPassportTS, dateOfPassportTS, placeOfIssuanceOfPassportTS);
        passportLayot.add(sixLayoutH);

        FlexLayout sevenLayoutH = new FlexLayout();
        sevenLayoutH.setAlignItems(Alignment.BASELINE);
        TextField owner = new TextField("Собственник");

        Button addOwner = new Button(VaadinIcon.PLUS.create());
        addOwner.setEnabled(false);
        sevenLayoutH.add(owner, addOwner);
        passportLayot.add(sevenLayoutH);

        HorizontalLayout eightLayoutH = new HorizontalLayout();
        TextField cost = new TextField("Стоимость");
        binder.forField(cost).
                withValidator(new BigDecimalValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(cost, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return car.getPassportData().getCost() == null ? String.valueOf(0) : String.valueOf(car.getPassportData().getCost());
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getPassportData().setCost(new BigDecimal(s));
                    }
                });
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setAlignItems(Alignment.BASELINE);
        TextField group = new TextField("Группа");
        Button addGroup = new Button(VaadinIcon.PLUS.create());
        addGroup.setEnabled(false);
        flexLayout.add(group, addGroup);
        TextField regNumber = new TextField("Рег. знак");
        binder.forField(regNumber).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getRegNumber();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setRegNumber(s);
            }
        });
        TextField oldregNumber = new TextField("Рег. знак старый");
        binder.forField(oldregNumber).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getOldregNumber();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setOldregNumber(s);
            }
        });
        eightLayoutH.add(cost, flexLayout, regNumber, oldregNumber);
        passportLayot.add(eightLayoutH);

        HorizontalLayout nineLayoutH = new HorizontalLayout();
        TextField certificateOfRegistration = new TextField("Св-во №");
        binder.forField(certificateOfRegistration).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getCertificateOfRegistration();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setCertificateOfRegistration(s);
            }
        });
        TextField placeOfregistration = new TextField("Место регистрации");
        binder.forField(placeOfregistration).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return car.getPassportData().getPlaceOfregistration();
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setPlaceOfregistration(s);
            }
        });
        DatePicker dateOfRegistration = new DatePicker("Св-во дата ");
        binder.forField(dateOfRegistration).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getPassportData().getDateOfRegistration() == null ? null
                                : car.getPassportData().getDateOfRegistration().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getPassportData().setDateOfRegistration(date);
                    }
                });
        DatePicker tempRegistration = new DatePicker("Временная регистрация до");
        binder.forField(tempRegistration).
                bind(new ValueProvider<Car, LocalDate>() {
                    @Override
                    public LocalDate apply(Car car) {
                        return car.getPassportData().getDateTempRegistration() == null ? null
                                : car.getPassportData().getDateTempRegistration().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Car, LocalDate>() {
                    @Override
                    public void accept(Car car, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        car.getPassportData().setTempRegistration(date);
                    }
                });
        passportLayot.add(certificateOfRegistration, placeOfregistration, dateOfRegistration, tempRegistration);

        HorizontalLayout tenLayout = new HorizontalLayout();
        ComboBox<EnumTypeOfBody> typeOfBody = new ComboBox<>();
        typeOfBody.setLabel("Тип кузова");
        binder.forField(typeOfBody).bind(new ValueProvider<Car, EnumTypeOfBody>() {
            @Override
            public EnumTypeOfBody apply(Car car) {
                return car.getPassportData().getTypeOfBody();
            }
        }, new Setter<Car, EnumTypeOfBody>() {
            @Override
            public void accept(Car car, EnumTypeOfBody s) {
                car.getPassportData().setTypeOfBody(s);
            }
        });
        typeOfBody.setItems(EnumSet.allOf(EnumTypeOfBody.class));
        TextField quantityOfPallet = new TextField("Кол-во палет");
        binder.forField(quantityOfPallet)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(quantityOfPallet, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return String.valueOf(car.getPassportData().getQuantityOfPallet());
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getPassportData().setQuantityOfPallet(Integer.parseInt(s));
                    }
                });
        TextField lenghtOfBody = new TextField("Длина");
        binder.forField(lenghtOfBody)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(lenghtOfBody, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return String.valueOf(car.getPassportData().getLenghtOfBody());
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getPassportData().setLenghtOfBody(Double.parseDouble(s));
                    }
                });
        TextField widhtOfBody = new TextField("Ширина");
        binder.forField(widhtOfBody)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(widhtOfBody, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return String.valueOf(car.getPassportData().getWidhtOfBody());
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getPassportData().setWidhtOfBody(Double.parseDouble(s));
                    }
                });
        TextField heightOfBody = new TextField("Высота");
        binder.forField(heightOfBody)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(heightOfBody, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Car, String>() {
                    @Override
                    public String apply(Car car) {
                        return String.valueOf(car.getPassportData().getHeightOfBody());
                    }
                }, new Setter<Car, String>() {
                    @Override
                    public void accept(Car car, String s) {
                        car.getPassportData().setHeightOfBody(Double.parseDouble(s));
                    }
                });
        TextField volumeOfBody = new TextField("Обьем фургона куб");
        binder.forField(volumeOfBody)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(volumeOfBody, status);
                    setEnableSubmit();
                }).bind(new ValueProvider<Car, String>() {
            @Override
            public String apply(Car car) {
                return String.valueOf(car.getPassportData().getVolumeOfBody());
            }
        }, new Setter<Car, String>() {
            @Override
            public void accept(Car car, String s) {
                car.getPassportData().setVolumeOfBody(Double.parseDouble(s));
            }
        });
        tenLayout.add(typeOfBody, quantityOfPallet, lenghtOfBody, widhtOfBody, heightOfBody, volumeOfBody);
        passportLayot.add(tenLayout);

        passportLayot.setVisible(false);
        pasport.add(passportLayot);
        mapTabs.put(pasport, passportLayot);

        return pasport;
    }

    @Override
    protected void prepareItem(Car car) {
        boolean persisted = car.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            item = (Car) itemService.getById(car.getId());
        } else {
            item = car;
            PassportData passportData = new PassportData();
            GeneralData generalData = new GeneralData();
            item.setGeneralData(generalData);
            item.setPassportData(passportData);
        }
    }
}
