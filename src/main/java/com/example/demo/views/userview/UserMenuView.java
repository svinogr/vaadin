package com.example.demo.views.userview;

import com.example.demo.entity.users.EnumUserColumnNameForUser;
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

@UIScope
@SpringComponent
public class UserMenuView extends AbstractMenuView<EnumUserColumnNameForUser> {
    @Override
    protected void createSearchMenu() {
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumUserColumnNameForUser.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));

        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumUserColumnNameForUser>, EnumUserColumnNameForUser>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumUserColumnNameForUser>, EnumUserColumnNameForUser> event) {
                additionalGreedMenuLayout.removeAll();
                changeSearchFields(event);

            }
        });

        additionalGreedMenuLayout = new HorizontalLayout();
        add(columnNamesComboBox, additionalGreedMenuLayout);
    }

    @Override
    protected void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumUserColumnNameForUser>, EnumUserColumnNameForUser> event) {
        if(event.getValue() == null){
            additionalGreedMenuLayout.removeAll();
            return;
        }

        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        switch (event.getValue()) {
            case SURNAME:
                additionalGreedMenuLayout.add(searchField);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
    }

    @Override
    public MyFilterItem getFilterItem() {
        MyFilterItem myFilterItem = null;
        EnumUserColumnNameForUser enumUserColumnNameForUser = columnNamesComboBox.getValue();

        if(enumUserColumnNameForUser == null){return null;}

        switch (enumUserColumnNameForUser) {
            case SURNAME:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumUserColumnNameForUser);
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
