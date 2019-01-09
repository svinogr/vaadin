package com.example.demo.views.organisationview;

import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.OneTextSearch;
import com.example.demo.services.search.OneTextValue;
import com.example.demo.services.search.Searchable;
import com.example.demo.views.AbstractMenuView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Arrays;

@SpringComponent
@UIScope
public class OrganisationMenuView extends AbstractMenuView<EnumColumnNameForOrg> {
    @Override
    protected void createSearchMenu() {
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNameForOrg.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));

        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg> event) {
                additionalGreedMenuLayout.removeAll();
                changeSearchFields(event);
            }
        });

        additionalGreedMenuLayout = new HorizontalLayout();
        add(columnNamesComboBox, additionalGreedMenuLayout);
    }

    @Override
    protected void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg> event) {
        if (event.getValue() == null) {
            additionalGreedMenuLayout.removeAll();
            return;
        }

        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);

        switch (event.getValue()) {
            case NAME:
                additionalGreedMenuLayout.add(searchField);
                break;
            case OGRN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case OKPO:
                additionalGreedMenuLayout.add(searchField);
                break;
            case INN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case EGRUL:
                additionalGreedMenuLayout.add(searchField);
                break;
            case KPP:
                additionalGreedMenuLayout.add(searchField);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
    }

    @Override
    public MyFilterItem getFilterItem() {
        MyFilterItem myFilterItem = null;

        EnumColumnNameForOrg enumColumnNamesForOrg = columnNamesComboBox.getValue();

        if (enumColumnNamesForOrg == null) {
            return null;
        }

        switch (enumColumnNamesForOrg) {
            case NAME:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case OGRN:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case OKPO:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case INN:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case EGRUL:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case KPP:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;
    }
}
