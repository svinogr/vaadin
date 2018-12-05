package com.example.demo.views;

import com.example.demo.entity.Selectable;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.StreamResourceWriter;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.server.FileDownloader;


import java.io.*;

public abstract class AbstractMiddleView extends VerticalLayout implements IdViewable {
    protected MenuInterface menuInterface;
    protected GridInterface gridInterface;

    public AbstractMiddleView(MenuInterface menuInterface, GridInterface gridInterface) {
        this.menuInterface = menuInterface;
        this.gridInterface = gridInterface;

        HorizontalLayout btnLayout = new HorizontalLayout();
        Button searchBtn = new Button(VaadinIcon.SEARCH.create());

        btnLayout.add(searchBtn);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setAlignItems(Alignment.END);

        if(menuInterface instanceof Component) {
            Component component = (Component) menuInterface;
           searchLayout.add(component, btnLayout);
        }
        add(searchLayout);

        if(gridInterface instanceof Component){
            Component component = (Component) gridInterface;
        add(component);
        }


        searchBtn.addClickListener(e->{
           MyFilterItem myFilterItem = getMyFilterItem();
           gridInterface.searchByFilterItem(myFilterItem);
        });


    }

    protected MyFilterItem getMyFilterItem(){
        MyFilterItem myFilterItem =  menuInterface.getFilterItem();
        return myFilterItem;
    }

    public Selectable getSelectItem(){
        return gridInterface.getSelectedItem();
    }
}
