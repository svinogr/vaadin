package com.example.demo.editors;

import com.example.demo.services.ItemService;
import com.example.demo.services.UniqTestInterface;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstarctEditor<T> extends VerticalLayout {
    protected T item;
    protected Label title;
    protected ItemService itemService;
    protected Map<Tab, Component> mapTabs = new HashMap<>();
    protected Binder<T> binder = new Binder<>();
    private Tabs tabs;
    protected ChangeHandler changeHandler;
    protected Button save;
    protected Set<TextField> textFieldsList = new HashSet<>();

    public void setSaveButton(Button save) {
        this.save = save;
    }

    public AbstarctEditor(ItemService itemService) {
        this.itemService = itemService;
        title = new Label();
        add(title);
        setTitle();
        createTab();
    }

    // для создания заголовка
    abstract void setTitle();

    private void createTab() {
        tabs = new Tabs();
        createTabs(tabs);
        tabs.addSelectedChangeListener(event -> {
            Component pageToShown = mapTabs.get(tabs.getSelectedTab());
            for (Component page : mapTabs.values()) {
                if (page == pageToShown) {
                    page.setVisible(true);
                } else page.setVisible(false);
            }

        });

        add(tabs);
        //  Component pageToShown = mapTabs.get(tabs.getSelectedTab());
        for (Component page : mapTabs.values()) {
            add(page);
        }

    }

    abstract void createTabs(Tabs tabs);

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public void save() {
        if (!isValid()) {
            return;
        }

        if (haveNotUniqFields()) {
            return;
        }

        createOrUpdate(item);

        changeHandler.onChange();
    }

    protected void createOrUpdate(T item) {
        itemService.create(item);
    }

    abstract boolean haveNotUniqFields();

    protected boolean setUniqState(Map<Component, MyFilterItem> map, long id) {
        UniqTestInterface uniqTestInterface = (UniqTestInterface) itemService;
        boolean flag = false;

        for (Map.Entry<Component, MyFilterItem> mapItem : map.entrySet()) {
            if (!uniqTestInterface.isUniq(mapItem.getValue(), id)) {
                ((TextField) mapItem.getKey()).setInvalid(true);
                ((TextField) mapItem.getKey()).setErrorMessage("Данные в поле " + mapItem.getValue().getEnumColumnNamesFor().getDisplayName() + " должны быть уникальны");
                flag = true;
            } else {
                ((TextField) mapItem.getKey()).setInvalid(false);
            }

        }

        return flag;

    }

    private boolean isValid() {
        binder.validate();
        return binder.isValid();
    }

    @Transactional
    public void delete() {
        itemService.delete(item);
        changeHandler.onChange();
    }

    @Transactional
    public void edit(T c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        prepareItem(c);
        binder.setBean(item);
        setVisible(true);

    }

    protected abstract void prepareItem(T item);

}


