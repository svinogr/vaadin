package com.example.demo.views.personalview;

import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.services.search.*;
import com.example.demo.views.AbstractMenuView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@SpringComponent
@UIScope
public class PersonalMenuView extends AbstractMenuView<EnumColumnNamesForPerson> {
    private ComboBox<EnumTypePerson> typePersonComboBox;

    @Override
    protected void createSearchMenu() {
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNamesForPerson.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));

        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson> event) {
                additionalGreedMenuLayout.removeAll();
                changeSearchFields(event);

            }
        });

        additionalGreedMenuLayout = new HorizontalLayout();
        add(columnNamesComboBox, additionalGreedMenuLayout);
    }

    @Override
    protected void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson> event) {
        if (event.getValue() == null) {
            additionalGreedMenuLayout.removeAll();
            return;
        }

        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
        typePersonComboBox = new ComboBox<>();
        typePersonComboBox.setLabel(label);
        switch (event.getValue()) {
            case DATE_OF_BIRTH:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case CABINET:
                additionalGreedMenuLayout.add(searchField);
                break;
            case SURNAME:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_PERSON:
                typePersonComboBox.setItems(EnumTypePerson.values());
                additionalGreedMenuLayout.add(typePersonComboBox);
                break;
            case FIRED:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            default:
        }
    }

    @Override
    public MyFilterItem getFilterItem() {
        MyFilterItem myFilterItem = null;
        EnumColumnNamesForPerson enumColumnNamesForPerson = columnNamesComboBox.getValue();

        if (enumColumnNamesForPerson == null) {
            return null;
        }

        switch (enumColumnNamesForPerson) {
            case DATE_OF_BIRTH:
                if (startDate.getValue() != null && finishDate != null) {
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case CABINET:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case SURNAME:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_PERSON:
                if (typePersonComboBox.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(typePersonComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case FIRED:
                EnumYesNo value = yesNOComboBox.getValue();
                if (value != null) {
                    Checkable check = new Check(value.isYes());
                    myFilterItem = new CheckValue(enumColumnNamesForPerson);
                    myFilterItem.setCheckable(check);

                }

            default:
        }
        return myFilterItem;

    }
}
