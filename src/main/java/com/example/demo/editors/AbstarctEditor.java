package com.example.demo.editors;

import com.example.demo.editors.ChangeHandler;
import com.example.demo.services.ItemService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
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
    private ChangeHandler changeHandler;
    private Button save;
    private Set<TextField> textFieldsList = new HashSet<>();

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

    protected void setEnableSubmit() {
        boolean flag = true;
        for (TextField textField : textFieldsList) {
            if (textField.isInvalid()) {
                flag = false;
                break;
            }
        }
        if (save != null) {
            save.setEnabled(flag);
        }
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



    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public void save() {
        itemService.create(item);
        changeHandler.onChange();
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


