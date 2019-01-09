package com.example.demo.views.journalview;

import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.jornal.EnumColumnNameForJournal;
import com.example.demo.services.search.Check;
import com.example.demo.services.search.Checkable;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.ParentValue;
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
public class JournalMenuView extends AbstractMenuView<EnumColumnNameForJournal> {
    @Override
    protected void createSearchMenu() {
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNameForJournal.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));

        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForJournal>, EnumColumnNameForJournal>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForJournal>, EnumColumnNameForJournal> event) {
                additionalGreedMenuLayout.removeAll();
                changeSearchFields(event);

            }
        });

        additionalGreedMenuLayout = new HorizontalLayout();
        add(columnNamesComboBox, additionalGreedMenuLayout);
    }

    @Override
    protected void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForJournal>, EnumColumnNameForJournal> event) {
        if (event.getValue() == null) {
            additionalGreedMenuLayout.removeAll();
            return;
        }

        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
        switch (event.getValue()) {
            case CLOSED:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
    }

    @Override
    public MyFilterItem getFilterItem() {
        MyFilterItem myFilterItem = null;
        EnumColumnNameForJournal enumColumnNameForJournal = columnNamesComboBox.getValue();

        if (enumColumnNameForJournal == null) {
            return null;
        }

        switch (enumColumnNameForJournal) {
            case CLOSED:
                if (yesNOComboBox.getValue() != null) {
                    myFilterItem = new ParentValue(enumColumnNameForJournal);
                    Checkable check = new Check(yesNOComboBox.getValue().isYes());
                    myFilterItem.setCheckable(check);
                }
                break;
            default:
                System.out.println("Дефолтное значение");

        }
        return myFilterItem;
    }
}
