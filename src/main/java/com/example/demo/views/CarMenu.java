package com.example.demo.views;

import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.entity.cars.car.EnumTypeFuel;
import com.example.demo.entity.cars.car.EnumTypeOfBody;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.services.search.*;
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
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@SpringComponent
@UIScope
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
        MyFilterItem myFilterItem = null;
        EnumColumnNamesForCar enumColumnNamesForCar = columnNamesComboBox.getValue();

        if(enumColumnNamesForCar == null){return null;}

        switch (enumColumnNamesForCar) {
            case DATE_OF_TAKE_TO_BALLANCE:
                if(startDate.getValue() != null && finishDate != null){
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case DECOMISSIONED:
                EnumYesNo value = yesNOComboBox.getValue();
                if(value != null){
                    Checkable check = new Check(value.isYes());
                    myFilterItem = new CheckValue(enumColumnNamesForCar);
                    myFilterItem.setCheckable(check);
                }

                break;
            case DATE_OF_COMMISSIONED:
                if(startDate.getValue() != null && finishDate != null){
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }

                break;
            case FAULY:
                if(yesNOComboBox.getValue() != null){
                    myFilterItem = new CheckValue(enumColumnNamesForCar);
                    Checkable check = new Check(yesNOComboBox.getValue().isYes());
                    myFilterItem.setCheckable(check);
                }
                break;
            case PODRAZDELENIE_OR_GARAGE:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case COLONNA:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_GARAGE:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_INVENTAR:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_OF_FUEL:
                if(typeFuelComboBox.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(typeFuelComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case MILEAGE:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MASHINE_HOURS:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VIN:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_BODY:
                if(typeBodyComboBox.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(typeBodyComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case YEAR_OF_BUILD:
                if(startDate.getValue() != null && finishDate != null){
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case ECCO_OF_ENGINE:
                if(numberComboBox.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(numberComboBox.getValue().toString());
                    myFilterItem.setSearchable(oneTextSearch);
                }

                break;
            case NUMBER_OF_ENGINE:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_CHASSIS:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_BODY:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case POWER_OF_ENGINE:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VOLUME_OF_ENGINE:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MAX_MASS:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MAX_MASS_WITHOUT:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case NUMBER_OF_PASSPORT_TS:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case REG_NUMBER:
                if(searchField.getValue() != null){
                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case QUANTITY_OF_PALLET:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case WIDHT_OF_BODY:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case HEIGHT_OF_BODY:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case LENGHT_OF_BODY:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VOLUME_OF_BODY:
                if(from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;
    }
}
