package com.example.demo.views;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CarViewNew extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";

    public CarViewNew(CarMenu carMenu, CarGrid carGrid) {
      super(carMenu, carGrid);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

}
