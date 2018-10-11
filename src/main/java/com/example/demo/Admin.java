package com.example.demo;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "admin")
public class Admin extends VerticalLayout {
    public Admin() {

        add(new Label("admin"));

    }
}
