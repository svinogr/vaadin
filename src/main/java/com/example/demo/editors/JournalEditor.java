package com.example.demo.editors;

import com.example.demo.entity.jornal.EnumTypeOil;
import com.example.demo.entity.jornal.EnumTypeRecord;
import com.example.demo.entity.jornal.EnumTypeTO;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.ItemService;
import com.example.demo.services.JournalService;
import com.example.demo.validators.BigDecimalValidator;
import com.example.demo.validators.DoubleValidator;
import com.example.demo.validators.IntegerValidator;
import com.example.demo.validators.NullValidator;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
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


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@SpringComponent
@UIScope
public class JournalEditor extends AbstarctEditor<JournalItem> {

    public JournalEditor(JournalService journalService) {
        super(journalService);
    }

    @Override
    void setTitle() {
        title.setText("Карточка журнала");
    }

    @Override
    void createTabs(Tabs tabs) {
        Tab general = createGeneralTab();
        Tab setup = createSetupTab();
        Tab delete = createDeleteTab();
        tabs.add(general, setup, delete);
    }

    @Override
    protected void prepareItem(JournalItem journalItem) {
        boolean persisted =  journalItem.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            item = (JournalItem) itemService.getById(journalItem.getId());
        } else {
            item = journalItem;
        }

    }

    private Tab createGeneralTab() {
        Tab general = new Tab("Основное");

        VerticalLayout oneLayout = new VerticalLayout();

        HorizontalLayout subOneLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

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

        // TODO возможно стоит сделать валидатор
        ComboBox<EnumTypeTO> comboboxTypeOfTO = new ComboBox<>("ТО из регламента");
        comboboxTypeOfTO.setItems(EnumTypeTO.values());
        binder.forField(comboboxTypeOfTO)
                .bind(new ValueProvider<JournalItem, EnumTypeTO>() {
                    @Override
                    public EnumTypeTO apply(JournalItem journalItem) {
                        return journalItem.getTypeTo();
                    }
                }, new Setter<JournalItem, EnumTypeTO>() {
                    @Override
                    public void accept(JournalItem journalItem, EnumTypeTO enumTypeTO) {
                        journalItem.setTypeTo(enumTypeTO);
                    }
                });

        TextField model = new TextField("Модель");
        binder.forField(model)
                .bind(new ValueProvider<JournalItem, String>() {
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

        ComboBox<EnumTypeOil> comboboxTypeOil = new ComboBox<>("Вид масла/смазки");
        comboboxTypeOil.setItems(EnumTypeOil.values());
        binder.forField(comboboxTypeOil)
                .bind(new ValueProvider<JournalItem, EnumTypeOil>() {
                    @Override
                    public EnumTypeOil apply(JournalItem journalItem) {
                        return journalItem.getTypeOil();
                    }
                }, new Setter<JournalItem, EnumTypeOil>() {
                    @Override
                    public void accept(JournalItem journalItem, EnumTypeOil enumTypeOil) {
                        journalItem.setTypeOil(enumTypeOil);
                    }
                });

        TextField code = new TextField("Номер/код");
        binder.forField(code).bind(new ValueProvider<JournalItem, String>() {
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

        ComboBox<EnumTypeRecord> typeRecordComboBox = new ComboBox<>("Вид записи");
        typeRecordComboBox.setItems(EnumTypeRecord.values());
        typeRecordComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumTypeRecord>, EnumTypeRecord>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumTypeRecord>, EnumTypeRecord> event) {
                EnumTypeRecord enumTypeRecord;
                model.setVisible(true);
                // setup.setVisible(true);
                comboboxTypeOfTO.setVisible(false);
                //  comboboxTypeOfTO.setValue(null);
                comboboxTypeOil.setVisible(false);
                //     comboboxTypeOil.setValue(null);
                if (event.getValue() != null) {

                    enumTypeRecord = event.getValue();

                    switch (enumTypeRecord) {
                        case TO:
                            model.setVisible(false);
                            comboboxTypeOfTO.setVisible(true);
                            //   setup.setVisible(false);
                            break;
                        case OIL:
                            comboboxTypeOil.setVisible(true);
                            break;
                    }
                }
            }
        });

        binder.forField(typeRecordComboBox).
                withValidator(new NullValidator())
                .withValidationStatusHandler(status -> {
                    setStatusComponent(typeRecordComboBox, status);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<JournalItem, EnumTypeRecord>() {
                    @Override
                    public EnumTypeRecord apply(JournalItem journalItem) {
                        System.out.println(journalItem.getEnumTypeRecord() + "1");

                        return journalItem.getEnumTypeRecord();
                    }
                }, new Setter<JournalItem, EnumTypeRecord>() {
                    @Override
                    public void accept(JournalItem journalItem, EnumTypeRecord enumTypeRecord) {
                        journalItem.setEnumTypeRecord(enumTypeRecord);
                    }
                });

        subOneLayoutH.add(typeRecordComboBox, comboboxTypeOfTO, name, comboboxTypeOil, model, code);

        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        subTwoLayoutH.setAlignItems(Alignment.BASELINE);

        Checkbox closed = new Checkbox("Запись закрыта");
        binder.forField(closed).bind(new ValueProvider<JournalItem, Boolean>() {
            @Override
            public Boolean apply(JournalItem journalItem) {
                return journalItem.isClosed();
            }
        }, new Setter<JournalItem, Boolean>() {
            @Override
            public void accept(JournalItem journalItem, Boolean aBoolean) {
                journalItem.setClosed(aBoolean);
            }
        });

        subTwoLayoutH.add(closed);

        oneLayout.add(subOneLayoutH, subTwoLayoutH);
        oneLayout.setVisible(true);
        // tab.add(oneLayout);

        mapTabs.put(general, oneLayout);
        return general;
    }
    private Tab createSetupTab() {
        Tab setup = new Tab("Установка на АТС");
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
        threeSubLayoutH.add(comment);

        oneLayoutV.add(subTwoLayoutH, threeSubLayoutH);
        oneLayoutV.setVisible(false);
        mapTabs.put(setup, oneLayoutV);

        return setup;
    }
    private Tab createDeleteTab() {
        Tab delete = new Tab("Списание с АТС");
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

        TextField cause = new TextField("Причина");
        binder.forField(cause).
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
        mapTabs.put(delete, oneLayoutV);
        return delete;
    }

}
