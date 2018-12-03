package com.example.demo.views;

import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public abstract class AbstractMenuView<IEnumColumnNames> extends VerticalLayout implements MenuInterface {
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
    protected TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
    protected TextField from = new TextField("От:");
    protected TextField to = new TextField("До:");
    protected DatePicker startDate = new DatePicker("С даты:");
    protected DatePicker finishDate = new DatePicker("По дату:");
    protected ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
    protected ComboBox<Integer> numberComboBox = new ComboBox<>();
    protected ComboBox<IEnumColumnNames> columnNamesComboBox;
    protected Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске

    protected IEnumColumnNames iEnumColumnNames = null;

    public AbstractMenuView() {
        createSearchMenu();
    }

    protected abstract void createSearchMenu();
    protected abstract void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<IEnumColumnNames>, IEnumColumnNames> event);
    public abstract MyFilterItem getFilterItem();
}
