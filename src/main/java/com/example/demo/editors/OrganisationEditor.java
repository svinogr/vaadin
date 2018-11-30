package com.example.demo.editors;

import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
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
    private Organisation organisation;
    private OrganisationService organisationService;
    private Binder<Organisation> binder = new Binder<>(Organisation.class);
    private ChangeHandler changeHandler;
    private Button save;
    private Tabs tabs;
    private Tab general;
    private Set<Component> componentList = new HashSet<>();
    private HashMap<Tab, Component> mapTabs = new HashMap<>();

    public void setSaveButton(Button save) {
        this.save = save;
    }

    public OrganisationEditor(OrganisationService organisationService) {
        this.organisationService = organisationService;
        Label title = new Label("Карточка организации");
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

        TextField nameOfOrganisation = new TextField("Назание организации");
        binder.forField(nameOfOrganisation).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getName();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setName(s);
            }
        });

        TextField address = new TextField("Адрес");
        binder.forField(address).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getAddress();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setAddress(s);
            }
        });

        TextField phone = new TextField("Телефон");
        binder.forField(phone).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getPhone();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setPhone(s);
            }
        });

        subOneLayoutH.add(nameOfOrganisation, address, phone);

        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        TextField okpo = new TextField("OКПО");
        binder.forField(okpo).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getOkpo();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setOkpo(s);
            }
        });

        TextField inn = new TextField("ИНН");
        binder.forField(inn).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getInn();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setInn(s);
            }
        });

        TextField ogrn = new TextField("ОГРН");
        binder.forField(ogrn).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getOgrn();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setOgrn(s);
            }
        });



        subTwoLayoutH.add(okpo, inn, ogrn);


        HorizontalLayout subThreeLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        TextField egrul = new TextField("ЕГРЮЛ");
        binder.forField(egrul).bind(new ValueProvider<Organisation, String>() {
            @Override
            public String apply(Organisation organisation) {
                return organisation.getEgrul();
            }
        }, new Setter<Organisation, String>() {
            @Override
            public void accept(Organisation organisation, String s) {
                organisation.setEgrul(s);
            }
        });

        DatePicker dateOfEgrul = new DatePicker();
        dateOfEgrul.setLabel("Дата ЕГРЮЛ");

        binder.forField(dateOfEgrul).
                bind(new ValueProvider<Organisation, LocalDate>() {
                    @Override
                    public LocalDate apply(Organisation organisation) {
                        return organisation.getDateOfEgurl() == null ? null
                                : organisation.getDateOfEgurl().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<Organisation, LocalDate>() {
                    @Override
                    public void accept(Organisation person, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        person.setDateOfEgurl(date);
                    }
                });

        subThreeLayoutH.add(egrul, dateOfEgrul);


        oneLayout.add(subOneLayoutH, subTwoLayoutH, subThreeLayoutH);
        oneLayout.setVisible(true);
        mapTabs.put(general, oneLayout);
        return general;
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public void save() {
        organisationService.create(organisation);
        changeHandler.onChange();
    }

    @Transactional
    public void delete() {
        organisationService.delete(organisation);
        changeHandler.onChange();
    }

    @Transactional
    public void edit(Organisation c) {
        if (c == null) {
            setVisible(false);
            return;
        }

        boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            organisation = organisationService.getById(c.getId());
            System.out.println(organisation);
        } else {

            organisation = c;
        }

        binder.setBean(organisation);
        setVisible(true);
    }

    public interface ChangeHandler {
        void onChange();
    }
}
