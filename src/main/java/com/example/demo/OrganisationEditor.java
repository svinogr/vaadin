package com.example.demo;

import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.PersonService;
import com.example.demo.validators.NullValidator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SpringComponent
@UIScope
public class OrganisationEditor extends VerticalLayout {
    private Person person;
    private PersonService personService;
    private Binder<Person> binder = new Binder<>(Person.class);
    private PersonEditor.ChangeHandler changeHandler;
    private Button save;
    private Tabs tabs;
    private Tab general;
    private Set<Component> componentList = new HashSet<>();
    private HashMap<Tab, Component> mapTabs = new HashMap<>();

    public void setSaveButton(Button save) {
        this.save = save;
    }

    public OrganisationEditor(PersonService personService) {
        this.personService = personService;
        Label title = new Label("Карточка сотрудника");
        add(title);
        createTab();
    }

    private void createTab() {
        Tab general = createGeneralTab();
        tabs = new Tabs();
        tabs.add(general);

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

    private void setEnableSubmit() {
        boolean flag = true;
        for (Component component : componentList) {
            if (component instanceof TextField) {
                TextField textField = (TextField) component;
                flag = !textField.isInvalid();
            }
            if (component instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) component;
                flag = !comboBox.isInvalid();
            }
            if (!flag) {
                break;
            }
        }
        if (save != null) {
            save.setEnabled(flag);
        }
    }

    private void setStatusComponent(Component component, BindingValidationStatus bv) {
        componentList.add(component);
        String message;

        if (bv.isError()) {
            message = bv.getMessage().get().toString();
            if (component instanceof TextField) {
                TextField textField = (TextField) component;
                textField.setErrorMessage(message);
                textField.setInvalid(true);
            }
            if (component instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) component;
                comboBox.setErrorMessage(message);
                comboBox.setInvalid(true);
            }

        } else {
            if (component instanceof TextField) {
                TextField textField = (TextField) component;
                textField.setInvalid(false);
            }
            if (component instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) component;
                comboBox.setInvalid(false);
            }
        }
    }

    private Tab createGeneralTab() {
        general = new Tab("Основное");

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


        oneLayout.add(subOneLayoutH, subTwoLayoutH, subThreeLayoutH, subFourLayoutH);
        oneLayout.setVisible(true);
        mapTabs.put(general, oneLayout);
        return general;
    }

    public void setChangeHandler(PersonEditor.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public void save() {
        personService.create(person);
        changeHandler.onChange();
    }

    @Transactional
    public void delete() {
        personService.delete(person);
        System.out.println(person.getId());
        changeHandler.onChange();
    }

    @Transactional
    public void edit(Person c) {
        if (c == null) {
            setVisible(false);
            return;
        }

        boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            person = personService.getById(c.getId());
            System.out.println(person);
        } else {

            person = c;
            person.setEnumTypePerson(EnumTypePerson.DRIVER);
        }

        binder.setBean(person);
        setVisible(true);
    }

    public interface ChangeHandler {
        void onChange();
    }
}
