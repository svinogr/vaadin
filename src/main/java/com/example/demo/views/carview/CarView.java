package com.example.demo.views.carview;

import com.example.demo.download.excel.CarExcelItem;
import com.example.demo.views.AbstractMiddleView;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class CarView extends AbstractMiddleView {
    public final static String ID_VIEW = "CAR_VIEW";

    public CarView(CarMenuView carMenu, CarGridView carGridView, CarExcelItem carExcelItem) {
      super(carMenu, carGridView, carExcelItem);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

}
