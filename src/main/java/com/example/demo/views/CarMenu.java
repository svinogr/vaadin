package com.example.demo.views;

import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.entity.cars.car.EnumTypeFuel;
import com.example.demo.entity.cars.car.EnumTypeOfBody;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;

public class CarMenu extends VerticalLayout implements MenuInterface {
    private TextField searchField = new TextField("Строка поиска", "поиск");
    private HorizontalLayout searchFlexLayout;
    private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске

    private ComboBox<EnumColumnNamesForCar> columnNamesComboBox;
    private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
    private TextField from = new TextField("От:");
    private TextField to = new TextField("До:");
    private DatePicker startDate = new DatePicker("С даты:");
    private DatePicker finishDate = new DatePicker("По дату:");
    private ComboBox<EnumTypeFuel> typeFuelComboBox = new ComboBox("Тип топлива:");
    private ComboBox<Integer> numberComboBox = new ComboBox<>();
    private ComboBox<EnumTypeOfBody> typeBodyComboBox = new ComboBox<>("Тип кузова:");

    public CarMenu() {
        createSearchMenu();
    }

    private void createSearchMenu() {
        FlexLayout greedMenuLayout = new FlexLayout();
        FlexLayout searchLayout = new FlexLayout();
        greedMenuLayout.add(searchLayout);

        searchFlexLayout = new HorizontalLayout();
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNamesForCar.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));

        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar> event) {
                additionalGreedMenuLayout.removeAll();
                changeSearchFields(event);
            }
        });

        additionalGreedMenuLayout = new Div();
        searchFlexLayout.add(columnNamesComboBox, additionalGreedMenuLayout);
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        greedMenuLayout.add(searchFlexLayout);
        add(greedMenuLayout);
    }

    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar> event) {
        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
        switch (event.getValue()) {
            case DATE_OF_TAKE_TO_BALLANCE:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case DECOMISSIONED:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            case DATE_OF_COMMISSIONED:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case FAULY:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            case PODRAZDELENIE_OR_GARAGE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case COLONNA:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_GARAGE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_INVENTAR:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_OF_FUEL:
                typeFuelComboBox.setItems(EnumTypeFuel.values());
                additionalGreedMenuLayout.add(typeFuelComboBox);
                break;
            case MILEAGE:
                additionalGreedMenuLayout.add(from, to);
                break;
            case MASHINE_HOURS:
                additionalGreedMenuLayout.add(from, to);
                break;
            case VIN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_BODY:
                typeBodyComboBox.setItems(EnumTypeOfBody.values());
                additionalGreedMenuLayout.add(typeBodyComboBox);
                break;
            case YEAR_OF_BUILD:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case ECCO_OF_ENGINE:
                numberComboBox.setLabel(label);
                numberComboBox.setItems(1, 2, 3, 4, 5);
                additionalGreedMenuLayout.add(numberComboBox);
                break;
            case NUMBER_OF_ENGINE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_CHASSIS:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            case POWER_OF_ENGINE:
                additionalGreedMenuLayout.add(from, to);
                break;
            case VOLUME_OF_ENGINE:
                additionalGreedMenuLayout.add(from, to);
                break;
            case MAX_MASS:
                additionalGreedMenuLayout.add(from, to);
                break;
            case MAX_MASS_WITHOUT:
                additionalGreedMenuLayout.add(from, to);
                break;
            case NUMBER_OF_PASSPORT_TS:
                additionalGreedMenuLayout.add(searchField);
                break;
            case REG_NUMBER:
                additionalGreedMenuLayout.add(searchField);
                break;
            case QUANTITY_OF_PALLET:
                additionalGreedMenuLayout.add(from, to);
                break;
            case WIDHT_OF_BODY:
                additionalGreedMenuLayout.add(from, to);
                break;
            case HEIGHT_OF_BODY:
                additionalGreedMenuLayout.add(from, to);
                break;
            case LENGHT_OF_BODY:
                additionalGreedMenuLayout.add(from, to);
                break;
            case VOLUME_OF_BODY:
                additionalGreedMenuLayout.add(from, to);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

    }

    @Override
    public MyFilterItem getFilterItem() {
        return null;
    }
}
