package com.example.demo.editors;

import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
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
public class OrganisationEditorG extends AbstarctEditor<Organisation> {

    public OrganisationEditorG(OrganisationService itemService) {
        super(itemService);
    }

    @Override
    void setTitle() {
    title.setText("Картчока организации");
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

        HorizontalLayout subEightLayoutH = new HorizontalLayout();

        TextField chancged = new TextField("Изменено пользователем");
        chancged.setEnabled(false);
        binder.forField(chancged)
                .bind(new ValueProvider<Organisation, String>() {
                    @Override
                    public String apply(Organisation organisation) {
                        return organisation.getChanged() == null ? "" : organisation.getChanged();
                    }
                }, new Setter<Organisation, String>() {
                    @Override
                    public void accept(Organisation organisation, String s) {

                    }
                });


        subEightLayoutH.add(chancged);
        chancged.setWidth("400px");

        oneLayout.add(subOneLayoutH, subTwoLayoutH, subThreeLayoutH, subEightLayoutH);
        oneLayout.setVisible(true);
        oneLayout.setAlignSelf(Alignment.END, subEightLayoutH);
        mapTabs.put(general, oneLayout);
        return general;
    }

    @Override
    protected void prepareItem(Organisation organisation) {
        boolean persisted = organisation.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            item = (Organisation) itemService.getById(organisation.getId());
        } else {
            item = organisation;
        }
        binder.setBean(item);
        setVisible(true);
    }
}
