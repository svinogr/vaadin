package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.KeyPressEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;

import java.util.HashMap;
import java.util.Map;


public class MyGrid extends Grid<Car> {

    private Map<String, TextField> searchTextFields = new HashMap<>();
    private Search search;

    @Override
    public Column<Car> addColumn(ValueProvider<Car, ?> valueProvider) {
        return super.addColumn(valueProvider);
    }

    public Column<Car> addColumnWithHeaderTextField(ValueProvider<Car, ?> valueProvider, String labelTextField, String id) {
        Column<Car> carColumn = super.addColumn(valueProvider);
        carColumn.setHeader(createSearchHeader(labelTextField, id));
        return carColumn;
    }

    private VerticalLayout createSearchHeader(String nameLabel, String id) {
        VerticalLayout verticalLayout = new VerticalLayout();

        Label label = new Label(nameLabel);

        FlexLayout flexLayout = new FlexLayout();
        TextField textField = new TextField();
        textField.setId(id);
        textField.setPlaceholder("поиск по " + nameLabel);
        searchTextFields.put(id, textField);
        Button searchBtn = new Button(VaadinIcon.SEARCH.create());
        searchBtn.setId(id);
        flexLayout.add(textField, searchBtn);

        searchBtn.addClickListener((s)->{

            for(Map.Entry<String, TextField> map : searchTextFields.entrySet()){
               if(map.getValue() != textField){
                   map.getValue().setValue("");
               }
           }

            search.search(textField.getId().get());
        });

        verticalLayout.add(label, flexLayout);
        return verticalLayout;

    }

    public Map<String, TextField> getSearchTextField() {
        return searchTextFields;
    }

    public void setSearchTextField(Map<String, TextField> searchTextFields) {
        this.searchTextFields = searchTextFields;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    interface Search{
        void search(String idTextField);
    }
}
