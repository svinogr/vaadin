package com.example.demo.views.carview;

import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";

    public CarView(CarMenuView carMenu, CarGridView carGridView) {
      super(carMenu, carGridView);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

}
