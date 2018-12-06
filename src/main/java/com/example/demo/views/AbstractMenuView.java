package com.example.demo.views;

import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.validators.DoubleValidator;
import com.example.demo.validators.IntegerValidator;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMenuView<IEnumColumnNames> extends HorizontalLayout implements MenuInterface {
    protected ValidationAction validationAction;
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
    protected TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
    protected TextField from = new TextField("От:");
    protected TextField to = new TextField("До:");
    protected DatePicker startDate = new DatePicker("С даты:");
    protected DatePicker finishDate = new DatePicker("По дату:");
    protected ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
    protected ComboBox<Integer> numberComboBox = new ComboBox<>();
    protected ComboBox<IEnumColumnNames> columnNamesComboBox;
    protected HorizontalLayout additionalGreedMenuLayout; // лайяут для доп выбора при поиске
    private Set<TextField> textFieldsList = new HashSet<>();
    private Binder<String> integerBinder = new Binder();

    protected IEnumColumnNames iEnumColumnNames = null;

    public AbstractMenuView() {
        createSearchMenu();
        Binder<String> binder = new Binder<>();

        from.setValue("0");
        binder.forField(from)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(e ->{
                    setStatusComponent(from, e);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<String, String>() {
                    @Override
                    public String apply(String string) {
                        return string;
                    }
                }, new Setter<String, String>() {
                    @Override
                    public void accept(String string, String s) {

                    }
                });

        to.setValue("0");
        binder.forField(to)
                .withValidator(new DoubleValidator())
                .withValidationStatusHandler(e ->{
                    setStatusComponent(to, e);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<String, String>() {
                    @Override
                    public String apply(String string) {
                        return string;
                    }
                }, new Setter<String, String>() {
                    @Override
                    public void accept(String string, String s) {

                    }
                });

    }

    protected void setEnableSubmit() {
        boolean flag = true;
        for (TextField textField : textFieldsList) {
            if (textField.isInvalid()) {
                flag = false;
                break;
            }
        }
       validationAction.disableComponent(flag);
    }

    protected void setStatusComponent(Component component, BindingValidationStatus bv) {
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


    protected abstract void createSearchMenu();

    protected abstract void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<IEnumColumnNames>, IEnumColumnNames> event);

    public abstract MyFilterItem getFilterItem();

    public ValidationAction getValidationAction() {
        return validationAction;


    }

    @Override
    public void setValidationAction(ValidationAction validationAction) {
        this.validationAction = validationAction;
    }
}