package com.example.demo;

import com.example.demo.entity.cars.car.*;
import com.example.demo.entity.cars.owner.Owner;
import com.example.demo.entity.jornal.EnumTypeRecord;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import com.example.demo.validators.BigDecimalValidator;
import com.example.demo.validators.DoubleValidator;
import com.example.demo.validators.IntegerValidator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
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


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@SpringComponent
@UIScope
public class JournalEditor extends VerticalLayout {
    private JournalItem journalItem;
    private JournalService journalService;
    private Binder<JournalItem> binder = new Binder<>(JournalItem.class);
    private JournalEditor.ChangeHandler changeHandler;
    private Button save;
    private Tabs tabs;
    private Set<TextField> textFieldsList = new HashSet<>();
    private HashMap<Tab, Component> mapTabs = new HashMap<>();

    public void setSaveButton(Button save) {
        this.save = save;
    }

    public JournalEditor(JournalService journalService) {
        this.journalService = journalService;
        Label title = new Label("Карточка записи журнала");
        add(title);
        createTab();
    }

    private void createTab() {
        Tab general = createGeneralTab();
        Tab setup = createSetupTab();
        Tab delete = createDeleteTab();
        tabs = new Tabs();
        tabs.add(general, setup, delete);

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

    private Tab createDeleteTab() {
        Tab tab = new Tab("Списание с АТС");
        VerticalLayout oneLayoutV = new VerticalLayout();

        HorizontalLayout fourSubLayoutH = new HorizontalLayout();
        fourSubLayoutH.setAlignItems(Alignment.BASELINE);
        DatePicker dateOfMileageDel = new DatePicker();
        dateOfMileageDel.setLabel("Дата");

        binder.forField(dateOfMileageDel).
                bind(new ValueProvider<JournalItem, LocalDate>() {
                    @Override
                    public LocalDate apply(JournalItem journalItem) {
                        return journalItem.getDatedelete() == null ? null
                                : journalItem.getDatedelete().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<JournalItem, LocalDate>() {
                    @Override
                    public void accept(JournalItem journalItem, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        journalItem.setDatedelete(date);
                    }
                });

        TextField mileageDel = new TextField("Пробег");
        binder.forField(mileageDel)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(mileageDel, status);
                    setEnableSubmit();
                }).
                bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return String.valueOf(journalItem.getDeleteMileage());
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setDeleteMileage(Double.parseDouble(s));
                    }
                });

        TextField cause = new TextField("Пробег");
        binder.forField(mileageDel).
                bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return journalItem.getCause();
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setCause(s);
                    }
                });

        fourSubLayoutH.add(dateOfMileageDel, mileageDel, cause);
        oneLayoutV.add(fourSubLayoutH);
        oneLayoutV.setVisible(false);
        mapTabs.put(tab, oneLayoutV);
        return tab;
    }

    private Tab createSetupTab() {
        Tab tab = new Tab("Установка на АТС");
        VerticalLayout oneLayoutV = new VerticalLayout();
        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        subTwoLayoutH.setAlignItems(Alignment.BASELINE);

        DatePicker dateOfMileage = new DatePicker();
        dateOfMileage.setLabel("Дата");

        binder.forField(dateOfMileage).
                bind(new ValueProvider<JournalItem, LocalDate>() {
                    @Override
                    public LocalDate apply(JournalItem journalItem) {
                        return journalItem.getDateSetup() == null ? null
                                : journalItem.getDateSetup().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                }, new Setter<JournalItem, LocalDate>() {
                    @Override
                    public void accept(JournalItem journalItem, LocalDate localDate) {
                        Date date = localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        journalItem.setDateSetup(date);
                    }
                });

        TextField mileage = new TextField("Пробег");
        binder.forField(mileage)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(mileage, status);
                    setEnableSubmit();
                }).
                bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return String.valueOf(journalItem.getSetupMileage());
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setSetupMileage(Double.parseDouble(s));
                    }
                });

        TextField cost = new TextField("Стоимость");
        binder.forField(cost)
                .withValidator(new BigDecimalValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(cost, status);
                    setEnableSubmit();
                }).
                bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return journalItem.getCost() == null ? String.valueOf(0) : String.valueOf(journalItem.getCost());
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setCost(new BigDecimal(s));
                    }
                });

        TextField qutitity = new TextField("Количество");
        binder.forField(qutitity)
                .withValidator(new IntegerValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(qutitity, status);
                    setEnableSubmit();
                }).
                bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return String.valueOf(journalItem.getQuantity());
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setQuantity(Integer.parseInt(s));
                    }
                });

        TextField typeOfUnits = new TextField("Еденицы измерения");
        binder.forField(typeOfUnits)
                .bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return journalItem.getTypeOfUnits();
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setTypeOfUnits(s);
                    }
                });

        //TODO Плательщик и  исполнитель организация
        subTwoLayoutH.add(dateOfMileage, mileage, cost, qutitity, typeOfUnits);

        HorizontalLayout threeSubLayoutH = new HorizontalLayout();
        threeSubLayoutH.setAlignItems(Alignment.BASELINE);

        TextField comment = new TextField(" Коментарий");
        binder.forField(comment)
                .bind(new ValueProvider<JournalItem, String>() {
                    @Override
                    public String apply(JournalItem journalItem) {
                        return journalItem.getComment();
                    }
                }, new Setter<JournalItem, String>() {
                    @Override
                    public void accept(JournalItem journalItem, String s) {
                        journalItem.setComment(s);
                    }
                });

        oneLayoutV.add(subTwoLayoutH, threeSubLayoutH);
        oneLayoutV.setVisible(false);
        mapTabs.put(tab, oneLayoutV);

        return tab;
    }


    private void setEnableSubmit() {
        boolean flag = true;
        for (TextField textField : textFieldsList) {
            if (textField.isInvalid()) {
                flag = false;
                break;
            }
        }
        if (save != null) {
            save.setEnabled(flag);
        }
    }

    private void setStatusComponent(Component component, BindingValidationStatus bv) {
        if (component instanceof TextField) {
            TextField textField = ((TextField) component);
            textFieldsList.add(textField);
            if (bv.isError()) {
                textField.setErrorMessage((String) bv.getMessage().get());
                textField.setInvalid(true);
            } else {
                textField.setInvalid(false);
            }
        }
    }

    private Tab createGeneralTab() {
        Tab tab = new Tab("Основное");

        VerticalLayout oneLayout = new VerticalLayout();

        HorizontalLayout subOneLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        ComboBox<EnumTypeRecord> typeRecordComboBox = new ComboBox<>("Вид записи");
        typeRecordComboBox.setItems(EnumTypeRecord.values());
        binder.forField(typeRecordComboBox).bind(new ValueProvider<JournalItem, EnumTypeRecord>() {
            @Override
            public EnumTypeRecord apply(JournalItem journalItem) {

                return journalItem.getEnumTypeRecord();
            }
        }, new Setter<JournalItem, EnumTypeRecord>() {
            @Override
            public void accept(JournalItem journalItem, EnumTypeRecord enumTypeRecord) {
                journalItem.setEnumTypeRecord(enumTypeRecord);
            }
        });

        //TODO сделать листенер для смены полей

        TextField name = new TextField("Имя");
        binder.forField(name).bind(new ValueProvider<JournalItem, String>() {
            @Override
            public String apply(JournalItem journalItem) {
                return journalItem.getName();
            }
        }, new Setter<JournalItem, String>() {
            @Override
            public void accept(JournalItem journalItem, String s) {
                journalItem.setName(s);
            }
        });

        TextField model = new TextField("Модель");
        binder.forField(name).bind(new ValueProvider<JournalItem, String>() {
            @Override
            public String apply(JournalItem journalItem) {
                return journalItem.getModel();
            }
        }, new Setter<JournalItem, String>() {
            @Override
            public void accept(JournalItem journalItem, String s) {
                journalItem.setModel(s);
            }
        });


        TextField code = new TextField("Номер/код");
        binder.forField(name).bind(new ValueProvider<JournalItem, String>() {
            @Override
            public String apply(JournalItem journalItem) {
                return journalItem.getCode();
            }
        }, new Setter<JournalItem, String>() {
            @Override
            public void accept(JournalItem journalItem, String s) {
                journalItem.setCode(s);
            }
        });

        subOneLayoutH.add(typeRecordComboBox, name, model, code);

        oneLayout.add(subOneLayoutH);
        oneLayout.setVisible(true);
       // tab.add(oneLayout);

        mapTabs.put(tab, oneLayout);
        return tab;
    }

    public void setChangeHandler(JournalEditor.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public void save() {
        journalService.create(journalItem);
        changeHandler.onChange();
    }

    @Transactional
    public void deleteJournal() {
        journalService.delete(journalItem);
        System.out.println(journalItem.getId());
        changeHandler.onChange();
    }

    @Transactional
    public void editJournal(JournalItem c) {
        if (c == null) {
            setVisible(false);
            return;
        }

        boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            journalItem = journalService.getById(c.getId());
        } else {
            journalItem = c;
        }

        binder.setBean(journalItem);
        setVisible(true);
    }

    public interface ChangeHandler {
        void onChange();
    }
}
