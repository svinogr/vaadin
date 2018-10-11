package com.example.demo;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "main")
public class MainView extends VerticalLayout {
    public MainView() {

        add(new Label("main"));

    }
}
