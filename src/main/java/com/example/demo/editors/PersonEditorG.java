package com.example.demo.editors;

import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.PersonService;
import com.example.demo.validators.NullValidator;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringComponent
@UIScope
public class PersonEditorG extends AbstarctEditor<Person> {
    public PersonEditorG(PersonService itemService) {
        super(itemService);
    }

    @Override
    void setTitle() {
        title.setText("Карточка персонала");
    }

    @Override
    void createTabs(Tabs tabs) {
        Tab general = createGeneralTab();
        tabs.add(general);
    }

    private Tab createGeneralTab() {
        Tab general = new Tab("Основное");

        VerticalLayout oneLayout = new VerticalLayout();

        HorizontalLayout subOneLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        //TODO сделать листенер для смены полей

        TextField surname = new TextField("Фамилия");
        binder.forField(surname).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getSurname();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setSurname(s);
            }
        });

        TextField name = new TextField("Имя");
        binder.forField(name).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setName(s);
            }
        });

        TextField patronymic = new TextField("Отчество");
        binder.forField(patronymic).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getPatronymic();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setPatronymic(s);
            }
        });

        DatePicker birthday = new DatePicker();
        birthday.setLabel("Дата рождения");

        binder.forField(birthday).
                bind(new ValueProvider<Person, LocalDate>() {
                    @Override
                    public LocalDate apply(Person person) {
                        return person.getBirthday() == null ? null
                                : person.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Person, LocalDate>() {
                    @Override
                    public void accept(Person person, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        person.setBirthday(date);
                    }
                });

        subOneLayoutH.add(surname, name, patronymic, birthday);

        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        DatePicker data_order = new DatePicker();
        data_order.setLabel("Дата приказа");
        binder.forField(data_order).
                bind(new ValueProvider<Person, LocalDate>() {
                    @Override
                    public LocalDate apply(Person person) {
                        return person.getDateOfOrder() == null ? null
                                : person.getDateOfOrder().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Person, LocalDate>() {
                    @Override
                    public void accept(Person person, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        person.setDateOfOrder(date);
                    }
                });

        TextField oerder_name = new TextField("Приказ");
        binder.forField(oerder_name).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getOrder();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setOrder(s);
            }
        });

        Checkbox fired = new Checkbox("Уволен");
        binder.forField(fired).bind(new ValueProvider<Person, Boolean>() {
            @Override
            public Boolean apply(Person person) {
                return person.isFired();
            }
        }, new Setter<Person, Boolean>() {
            @Override
            public void accept(Person person, Boolean aBoolean) {
                person.setFired(aBoolean);
            }
        });

        subTwoLayoutH.add(data_order, oerder_name, fired);


        HorizontalLayout subThreeLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        TextField numberOfTabel = new TextField("Табельный номер");
        binder.forField(numberOfTabel).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getNumberOfTabel();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setNumberOfTabel(s);
            }
        });

        TextField position = new TextField("Должность");
        binder.forField(position).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getPosition();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setPosition(s);
            }
        });

        ComboBox<EnumTypePerson> enumTypePersonComboBox = new ComboBox<>("Тип сотрудника");
        enumTypePersonComboBox.setItems(EnumTypePerson.values());
        binder.forField(enumTypePersonComboBox)
                .withValidator(new NullValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(enumTypePersonComboBox, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<Person, EnumTypePerson>() {
                    @Override
                    public EnumTypePerson apply(Person person) {
                        return person.getEnumTypePerson();
                    }
                }, new Setter<Person, EnumTypePerson>() {
                    @Override
                    public void accept(Person person, EnumTypePerson enumTypePerson) {
                        person.setEnumTypePerson(enumTypePerson);
                    }
                });

        subThreeLayoutH.add(position, numberOfTabel, enumTypePersonComboBox);

        HorizontalLayout subFourLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        TextField address = new TextField("Адрес");
        binder.forField(address).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getAddress();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setAddress(s);
            }
        });

        TextField phone = new TextField("Телефон");
        binder.forField(phone).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getPhone();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setPhone(s);
            }
        });


        TextField  comment = new TextField("Коментарий");
        binder.forField(comment).bind(new ValueProvider<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getComment();
            }
        }, new Setter<Person, String>() {
            @Override
            public void accept(Person person, String s) {
                person.setComment(s);
            }
        });

        subFourLayoutH.add(address, phone, comment);

        HorizontalLayout subEightLayoutH = new HorizontalLayout();

        TextField chancged = new TextField("Изменено пользователем");
        chancged.setEnabled(false);
        binder.forField(chancged)
                .bind(new ValueProvider<Person, String>() {
                    @Override
                    public String apply(Person person) {
                        return person.getChanged() == null ? "" : person.getChanged();
                    }
                }, new Setter<Person, String>() {
                    @Override
                    public void accept(Person person, String s) {

                    }
                });


        subEightLayoutH.add(chancged);
        chancged.setWidth("400px");

        oneLayout.add(subOneLayoutH, subTwoLayoutH, subThreeLayoutH, subFourLayoutH, subEightLayoutH);
        oneLayout.setVisible(true);
        oneLayout.setAlignSelf(Alignment.END, subEightLayoutH);
        mapTabs.put(general, oneLayout);
        return general;
    }

    @Override
    protected void prepareItem(Person person) {
        boolean persisted = person.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            item = (Person) itemService.getById(person.getId());
        } else {
            item = person;
            item.setEnumTypePerson(EnumTypePerson.DRIVER);
        }
        binder.setBean(item);
        setVisible(true);
    }
}
